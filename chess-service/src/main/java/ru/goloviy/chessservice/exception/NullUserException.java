package ru.goloviy.chessservice.exception;

public class NullUserException extends RuntimeException{
    public NullUserException() {
        super("one of the users is null");
    }
}
