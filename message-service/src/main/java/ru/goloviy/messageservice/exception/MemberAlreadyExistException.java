package ru.goloviy.messageservice.exception;

public class MemberAlreadyExistException extends RuntimeException{
    public MemberAlreadyExistException(Long chatId, Long userId){
        super(String.format("user with id %d already in chat with id %d", userId, chatId));
    }
}
