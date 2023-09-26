package ru.goloviy.messageservice.service;

import jakarta.servlet.http.HttpServletRequest;
import ru.goloviy.messageservice.model.User;

public interface PrincipalService {

    User getPrincipalUser(HttpServletRequest request);
}
