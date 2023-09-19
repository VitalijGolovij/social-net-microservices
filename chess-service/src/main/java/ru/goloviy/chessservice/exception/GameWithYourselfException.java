package ru.goloviy.chessservice.exception;

public class GameWithYourselfException extends RuntimeException{
    public GameWithYourselfException(){
        super("the game should only be played with different users");
    }
}
