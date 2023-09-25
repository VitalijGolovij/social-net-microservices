package ru.goloviy.profileservice.exception;

public class ImpossibleFriendException extends FriendException {
    public ImpossibleFriendException(){
        super("Impossible friendship");
    }
}
