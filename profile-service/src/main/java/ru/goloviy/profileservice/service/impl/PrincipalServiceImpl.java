package ru.goloviy.profileservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.goloviy.profileservice.model.User;
import ru.goloviy.profileservice.service.PrincipalService;
import ru.goloviy.profileservice.service.UserService;
import ru.goloviy.profileservice.util.JwtUtil;

import javax.servlet.http.HttpServletRequest;

@Service
public class PrincipalServiceImpl implements PrincipalService {
    private final JwtUtil jwtUtil;
    private final UserService userService;
    @Autowired
    public PrincipalServiceImpl(JwtUtil jwtUtil, UserService userService) {
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    @Override
    public User getPrincipalUser(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        String username = jwtUtil.getUsernameClaim(token.substring(7));
        return userService.getUserBy(username);
    }
}
