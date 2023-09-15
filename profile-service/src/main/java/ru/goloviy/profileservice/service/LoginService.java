package ru.goloviy.profileservice.service;

import ru.goloviy.profileservice.dto.request.UserLogin;
import ru.goloviy.profileservice.dto.response.LoginResponse;
import ru.goloviy.profileservice.util.JwtTokenResponse;

public interface LoginService {
    JwtTokenResponse login(UserLogin userLogin);
}
