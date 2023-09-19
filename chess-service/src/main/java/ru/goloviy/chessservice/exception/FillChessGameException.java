package ru.goloviy.chessservice.exception;

public class FillChessGameException extends RuntimeException{
    public FillChessGameException(){
        super("there are already two players in the chess game");
    }
}
