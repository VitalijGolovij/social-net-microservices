package ru.goloviy.securityservice.service;

import ru.goloviy.securityservice.dto.JwtToken;
import ru.goloviy.securityservice.dto.UserLoginCredential;
import ru.goloviy.securityservice.dto.UserRegisterCredential;

public interface AuthenticateService {
    JwtToken register(UserRegisterCredential registerCredential);
    JwtToken login(UserLoginCredential credential);
    void validateToken(JwtToken token);
}
