package ru.goloviy.profileservice.dto.response;

public abstract class ResultResponse {
    private final String code;
    public ResultResponse(String code){
        this.code = code;
    }
}
