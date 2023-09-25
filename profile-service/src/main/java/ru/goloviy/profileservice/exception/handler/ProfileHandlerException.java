package ru.goloviy.profileservice.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.goloviy.profileservice.dto.response.ErrorResponse;
import ru.goloviy.profileservice.dto.response.ResultResponse;
import ru.goloviy.profileservice.dto.response.ValidateExceptionResponse;
import ru.goloviy.profileservice.exception.FriendException;
import ru.goloviy.profileservice.exception.InvalidDataException;

@ControllerAdvice
public class ProfileHandlerException {
    @ExceptionHandler
    public ResponseEntity<ResultResponse> handleException(InvalidDataException e){
        ResultResponse response = new ValidateExceptionResponse("StrErr", e.getMessage(), e.getErrors());
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(FriendException e){
        ErrorResponse response = new ErrorResponse(e.getMessage());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
