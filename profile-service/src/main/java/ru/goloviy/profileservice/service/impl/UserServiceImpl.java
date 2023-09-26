package ru.goloviy.profileservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import ru.goloviy.profileservice.dto.UserDto;
import ru.goloviy.profileservice.dto.request.GetUserListRequest;
import ru.goloviy.profileservice.exception.InvalidDataException;
import ru.goloviy.profileservice.kafka.JsonKafkaProducer;
import ru.goloviy.profileservice.model.User;
import ru.goloviy.profileservice.repository.UserRepository;
import ru.goloviy.profileservice.service.ExampleService;
import ru.goloviy.profileservice.service.PaginationService;
import ru.goloviy.profileservice.service.UserService;
import ru.goloviy.profileservice.validator.ErrorsValidationProcessor;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PaginationService paginationService;
    private final ExampleService<User, UserDto> exampleService;
    private final ErrorsValidationProcessor errorsValidationProcessor;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PaginationService paginationService,
                           ExampleService<User, UserDto> exampleService, ErrorsValidationProcessor errorsValidationProcessor) {
        this.userRepository = userRepository;
        this.paginationService = paginationService;
        this.exampleService = exampleService;
        this.errorsValidationProcessor = errorsValidationProcessor;
    }

    @Override
    @Transactional
    public User getUserBy(String username) {
        Optional<User> user = userRepository.getUserByUsername(username);
        if(user.isEmpty()){
            throw new UsernameNotFoundException(
                    String.format("User with username '%s' not found", username)
            );
        }
        return user.get();
    }

    @Override
    @Transactional
    public User getUserBy(Long id) {
        Optional<User> user = userRepository.getUserById(id);
        if(user.isEmpty()){
            throw new UsernameNotFoundException(
                    String.format("User with id '%s' not found", id)
            );
        }
        return user.get();
    }

    @Override
    @Transactional
    public List<User> getUserList(GetUserListRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InvalidDataException(errorsValidationProcessor.processErrors(bindingResult));
        }
        if (request == null){
            return userRepository.findAll();
        }
        Pageable pageable = paginationService.getPageable(request.getPagination());
        Example<User> example = exampleService.getExample(request.getUserFilter());
        return userRepository.findAll(example, pageable).getContent();
    }
}
