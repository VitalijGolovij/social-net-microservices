package ru.goloviy.profileservice.dto.response;

public abstract class ExceptionResponse extends ResultResponse{
    private final String message;
    public ExceptionResponse(String code, String message) {
        super(code);
        this.message = message;
    }
}
