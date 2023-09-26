package ru.goloviy.securityservice.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.goloviy.securityservice.dto.response.ExceptionResponse;
import ru.goloviy.securityservice.dto.response.ValidateExceptionResponse;
import ru.goloviy.securityservice.dto.response.ResultResponse;
import ru.goloviy.securityservice.exception.InvalidDataException;

@ControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ResultResponse> handleException(InvalidDataException e){
        ResultResponse response = new ValidateExceptionResponse("StrErr", e.getMessage(), e.getErrors());
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }
    @ExceptionHandler
    public ResponseEntity<ResultResponse> handleException(BadCredentialsException e){
        ExceptionResponse response = new ExceptionResponse("StrErr", e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }
}
