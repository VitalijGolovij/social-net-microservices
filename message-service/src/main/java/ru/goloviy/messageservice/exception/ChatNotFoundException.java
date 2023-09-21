package ru.goloviy.messageservice.exception;

public class ChatNotFoundException extends RuntimeException{
    public ChatNotFoundException(Long id){
        super(String.format("chat with id %d not exist",id));
    }
}
