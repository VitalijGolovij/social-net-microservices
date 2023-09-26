package ru.goloviy.messageservice.service.impl;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.goloviy.messageservice.model.User;
import ru.goloviy.messageservice.service.PrincipalService;
import ru.goloviy.messageservice.service.UserService;
import ru.goloviy.messageservice.util.JwtUtil;

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
