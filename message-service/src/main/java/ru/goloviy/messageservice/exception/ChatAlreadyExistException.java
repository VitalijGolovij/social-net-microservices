package ru.goloviy.messageservice.exception;

public class ChatAlreadyExistException extends RuntimeException{
    public ChatAlreadyExistException(){
        super("chat already exist");
    }
}
