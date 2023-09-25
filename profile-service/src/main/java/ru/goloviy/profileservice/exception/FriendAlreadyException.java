package ru.goloviy.profileservice.exception;

public class FriendAlreadyException extends FriendException {
    private static final String ERROR_MESSAGE_TEMPLATE = "Friendship between user_id=%s and user_id=%s is already";
    public FriendAlreadyException(Long userId1, Long userId2){
        super(String.format(ERROR_MESSAGE_TEMPLATE, userId1, userId2));
    }
}
