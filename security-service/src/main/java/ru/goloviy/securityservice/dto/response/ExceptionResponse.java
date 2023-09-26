package ru.goloviy.securityservice.dto.response;

import lombok.Getter;

@Getter
public class ExceptionResponse extends ResultResponse {
    private final String msg;
    public ExceptionResponse(String code, String msg) {
        super(code);
        this.msg = msg;
    }
}
