package ru.goloviy.filestorageservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.goloviy.filestorageservice.exception.UserNotFoundException;
import ru.goloviy.filestorageservice.model.User;
import ru.goloviy.filestorageservice.repository.UserRepository;
import ru.goloviy.filestorageservice.service.UserService;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUserBy(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent())
            return userOptional.get();
        throw new UserNotFoundException(id);
    }
}
