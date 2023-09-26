package ru.goloviy.filestorageservice.service.impl;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.goloviy.filestorageservice.model.User;
import ru.goloviy.filestorageservice.service.PrincipalService;
import ru.goloviy.filestorageservice.service.UserService;
import ru.goloviy.filestorageservice.util.JwtUtil;

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
