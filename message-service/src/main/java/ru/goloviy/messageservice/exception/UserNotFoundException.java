package ru.goloviy.messageservice.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(){
        super("user not found");
    }
}
