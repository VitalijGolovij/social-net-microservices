package ru.goloviy.profileservice.exception;

public class FriendNotFoundException extends FriendException {
    private static final String ERROR_MESSAGE_TEMPLATE = "Friendship between user_id=%s and user_id=%s not found";
    public FriendNotFoundException(Long userId1, Long userId2){
        super(String.format(ERROR_MESSAGE_TEMPLATE, userId1, userId2));
    }
}
