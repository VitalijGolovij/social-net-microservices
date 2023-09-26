package ru.goloviy.messageservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.goloviy.messageservice.exception.UserNotFoundException;
import ru.goloviy.messageservice.model.User;
import ru.goloviy.messageservice.repository.UserRepository;
import ru.goloviy.messageservice.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUserBy(Long id) {
        User user = userRepository.getUserById(id);
        if (user == null)
            throw new UserNotFoundException();
        return user;
    }
    @Override
    public User getUserBy(String username) {
        User user = userRepository.getUserByUsername(username);
        if (user == null)
            throw new UserNotFoundException();
        return user;
    }
}
