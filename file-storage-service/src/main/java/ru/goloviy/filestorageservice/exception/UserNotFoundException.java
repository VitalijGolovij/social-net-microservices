package ru.goloviy.filestorageservice.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(){
        super("user not found");
    }
}
