package ru.goloviy.securityservice.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.BindingResult;
import ru.goloviy.securityservice.dto.JwtToken;
import ru.goloviy.securityservice.dto.UserLoginCredential;
import ru.goloviy.securityservice.dto.UserRegisterCredential;
import ru.goloviy.securityservice.exception.InvalidDataException;
import ru.goloviy.securityservice.service.AuthenticateService;
import ru.goloviy.securityservice.validator.ErrorsValidationProcessor;
import ru.goloviy.securityservice.validator.UserRegisterCredentialValidator;

import java.util.Collections;

@ExtendWith(MockitoExtension.class)
public class AuthenticateControllerTest {
    @Mock
    private AuthenticateService authenticateService;
    @Mock
    private UserRegisterCredentialValidator validator;
    @Mock
    private ErrorsValidationProcessor errorsValidationProcessor;
    @InjectMocks
    private AuthenticateController authenticateController;

    @Test
    public void successRegister(){
        UserRegisterCredential credential = Mockito.mock(UserRegisterCredential.class);
        BindingResult bindingResult = Mockito.mock(BindingResult.class);
        Mockito.when(bindingResult.hasErrors()).thenReturn(false);
        JwtToken token = Mockito.mock(JwtToken.class);
        Mockito.when(authenticateService.register(credential)).thenReturn(token);

        Assertions.assertEquals(token, authenticateController.register(credential, bindingResult));
        Assertions.assertDoesNotThrow(()-> authenticateController.register(credential,bindingResult));
    }
    @Test
    public void invalidDataExceptionWhenRegister(){
        UserRegisterCredential credential = Mockito.mock(UserRegisterCredential.class);
        BindingResult bindingResult = Mockito.mock(BindingResult.class);
        Mockito.when(bindingResult.hasErrors()).thenReturn(true);
        Mockito.when(errorsValidationProcessor.processErrors(bindingResult)).thenReturn(Collections.emptyList());
        Assertions.assertThrows(InvalidDataException.class, () -> authenticateController.register(credential, bindingResult));
    }
    @Test
    public void successLogin(){
        UserLoginCredential credential = Mockito.mock(UserLoginCredential.class);
        JwtToken token = Mockito.mock(JwtToken.class);
        Mockito.when(authenticateService.login(credential)).thenReturn(token);

        Assertions.assertEquals(token, authenticateService.login(credential));
    }
}
