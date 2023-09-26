package ru.goloviy.filestorageservice.service;

import ru.goloviy.filestorageservice.model.User;

public interface UserService {
    User getUserBy(Long id);
    User getUserBy(String username);
}
