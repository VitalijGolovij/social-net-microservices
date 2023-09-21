package ru.goloviy.messageservice.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(Long id){
        super(String.format("user with id %d not found", id));
    }
}
