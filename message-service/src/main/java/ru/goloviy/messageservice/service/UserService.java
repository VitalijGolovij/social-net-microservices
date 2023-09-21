package ru.goloviy.messageservice.service;

import ru.goloviy.messageservice.model.User;

public interface UserService {
    User getUserBy(Long id);
}
