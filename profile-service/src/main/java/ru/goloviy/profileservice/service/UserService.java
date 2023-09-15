package ru.goloviy.profileservice.service;

import ru.goloviy.profileservice.model.User;

public interface UserService {
    User getUserByUsername(String username);
}
