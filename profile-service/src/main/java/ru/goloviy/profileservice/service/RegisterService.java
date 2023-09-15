package ru.goloviy.profileservice.service;

import org.springframework.validation.BindingResult;
import ru.goloviy.profileservice.dto.request.UserRegister;
import ru.goloviy.profileservice.util.JwtTokenResponse;

public interface RegisterService {
    JwtTokenResponse register(UserRegister userRegister, BindingResult bindingResult);
}
