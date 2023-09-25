package ru.goloviy.apigateway.exception;

public class InvalidJwtTokenException extends AuthException{
    public InvalidJwtTokenException(){
        super("invalid jwt token");
    }
}
