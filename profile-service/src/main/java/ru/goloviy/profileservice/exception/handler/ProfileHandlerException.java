package ru.goloviy.profileservice.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.goloviy.profileservice.dto.response.ResultResponse;
import ru.goloviy.profileservice.dto.response.ValidateExceptionResponse;
import ru.goloviy.profileservice.exception.InvalidDataException;

@ControllerAdvice
public class ProfileHandlerException {
    @ExceptionHandler
    public ResponseEntity<ResultResponse> handleException(InvalidDataException e){
        ResultResponse response = new ValidateExceptionResponse("StrErr", e.getMessage(), e.getErrors());
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }
}
