package ru.goloviy.filestorageservice.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.goloviy.filestorageservice.dto.ErrorResponse;
import ru.goloviy.filestorageservice.exception.ImageNotFoundException;
import ru.goloviy.filestorageservice.exception.SaveImageException;
import ru.goloviy.filestorageservice.exception.UserNotFoundException;

@ControllerAdvice
public class AppExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<?> handleException(ImageNotFoundException e){
        ErrorResponse response = new ErrorResponse(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler
    public ResponseEntity<?> handleException(SaveImageException e){
        ErrorResponse response = new ErrorResponse(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler
    public ResponseEntity<?> handleException(UserNotFoundException e){
        ErrorResponse response = new ErrorResponse(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
