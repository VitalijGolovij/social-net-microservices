package ru.goloviy.profileservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;
import ru.goloviy.profileservice.dto.request.UserLogin;
import ru.goloviy.profileservice.service.LoginService;
import ru.goloviy.profileservice.util.JwtTokenResponse;
import ru.goloviy.profileservice.util.JwtUtil;

@Component
public class LoginServiceImpl implements LoginService {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @Autowired
    public LoginServiceImpl(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public JwtTokenResponse login(UserLogin userLogin) {
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(userLogin.getUsername(), userLogin.getPassword());
        authenticationManager.authenticate(token);
        return jwtUtil.generateToken(userLogin.getUsername());
    }
}
