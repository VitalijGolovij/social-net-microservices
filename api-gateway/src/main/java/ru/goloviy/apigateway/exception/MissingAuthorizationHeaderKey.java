package ru.goloviy.apigateway.exception;

public class MissingAuthorizationHeaderKey extends AuthException{
    public MissingAuthorizationHeaderKey(){
        super("request does not have key 'Authorization'");
    }
}
