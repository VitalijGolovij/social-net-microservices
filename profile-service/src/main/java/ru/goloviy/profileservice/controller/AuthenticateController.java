package ru.goloviy.profileservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.goloviy.profileservice.dto.request.UserLogin;
import ru.goloviy.profileservice.dto.request.UserRegister;
import ru.goloviy.profileservice.service.LoginService;
import ru.goloviy.profileservice.service.RegisterService;
import ru.goloviy.profileservice.util.JwtTokenResponse;

import javax.validation.Valid;

@RestController
@RequestMapping("/authdeprecated")
public class AuthenticateController {
    private final RegisterService registerService;
    private final LoginService loginService;

    @Autowired
    public AuthenticateController(RegisterService registerService, LoginService loginService) {
        this.registerService = registerService;
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public ResponseEntity<JwtTokenResponse> login(@RequestBody UserLogin userLogin){
        JwtTokenResponse response = loginService.login(userLogin);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<JwtTokenResponse> register(@RequestBody @Valid UserRegister userRegister,
                                    BindingResult bindingResult){
        JwtTokenResponse response = registerService.register(userRegister, bindingResult);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
