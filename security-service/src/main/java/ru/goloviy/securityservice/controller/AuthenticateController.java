package ru.goloviy.securityservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import jakarta.ws.rs.HeaderParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.goloviy.securityservice.dto.JwtToken;
import ru.goloviy.securityservice.dto.UserLoginCredential;
import ru.goloviy.securityservice.dto.UserRegisterCredential;
import ru.goloviy.securityservice.exception.InvalidDataException;
import ru.goloviy.securityservice.service.AuthenticateService;
import ru.goloviy.securityservice.validator.ErrorsValidationProcessor;
import ru.goloviy.securityservice.validator.UserRegisterCredentialValidator;

//TODO validate register dto
@RestController
@RequestMapping("/auth")
public class AuthenticateController {
    private final AuthenticateService authenticateService;
    private final UserRegisterCredentialValidator validator;
    private final ErrorsValidationProcessor errorsValidationProcessor;
    @Autowired
    public AuthenticateController(AuthenticateService authenticateService, UserRegisterCredentialValidator validator, ErrorsValidationProcessor errorsValidationProcessor) {
        this.authenticateService = authenticateService;
        this.validator = validator;
        this.errorsValidationProcessor = errorsValidationProcessor;
    }
    @Operation(summary = "register new user")
    @PostMapping("/register")
    public JwtToken register(@RequestBody @Valid UserRegisterCredential credential,
                             BindingResult bindingResult){
        validator.validate(credential, bindingResult);
        if (bindingResult.hasErrors())
            throw new InvalidDataException(errorsValidationProcessor.processErrors(bindingResult));
        return authenticateService.register(credential);
    }
    @Operation(summary = "Login user")
    @PostMapping("/login")
    public JwtToken login(@RequestBody UserLoginCredential credential){
        return authenticateService.login(credential);
    }

    @PostMapping("/validate-token")
    public ResponseEntity<?> validateToken(@RequestBody JwtToken jwtToken){
         authenticateService.validateToken(jwtToken);
         return new ResponseEntity<>(HttpStatus.OK);
    }
}
