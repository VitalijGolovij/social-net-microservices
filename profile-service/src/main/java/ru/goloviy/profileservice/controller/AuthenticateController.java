package ru.goloviy.profileservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.goloviy.profileservice.dto.request.UserLogin;
import ru.goloviy.profileservice.dto.request.UserRegister;
import ru.goloviy.profileservice.service.LoginService;
import ru.goloviy.profileservice.service.RegisterService;
import ru.goloviy.profileservice.util.JwtTokenResponse;

@RestController
@RequestMapping("/auth")
public class AuthenticateController {
    private final RegisterService registerService;
    private final LoginService loginService;

    @Autowired
    public AuthenticateController(RegisterService registerService, LoginService loginService) {
        this.registerService = registerService;
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public JwtTokenResponse login(@RequestBody UserLogin userLogin,
                                 BindingResult bindingResult){
        return loginService.login(userLogin);
    }

    @PostMapping("/register")
    public JwtTokenResponse register(@RequestBody UserRegister userRegister,
                                    BindingResult bindingResult){
        return registerService.register(userRegister, bindingResult);
    }
}
