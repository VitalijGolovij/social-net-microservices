package ru.goloviy.profileservice.dto.response;

import lombok.Getter;

@Getter
public abstract class ExceptionResponse extends ResultResponse{
    private final String msg;
    public ExceptionResponse(String code, String msg) {
        super(code);
        this.msg = msg;
    }
}
