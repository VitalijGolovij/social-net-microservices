package ru.goloviy.filestorageservice.service;


import jakarta.servlet.http.HttpServletRequest;
import ru.goloviy.filestorageservice.model.User;

public interface PrincipalService {

    User getPrincipalUser(HttpServletRequest request);
}
