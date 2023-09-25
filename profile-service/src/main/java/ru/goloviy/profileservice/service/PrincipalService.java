package ru.goloviy.profileservice.service;

import ru.goloviy.profileservice.model.User;

import javax.servlet.http.HttpServletRequest;

public interface PrincipalService {

    User getPrincipalUser(HttpServletRequest request);
}
