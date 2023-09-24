package ru.goloviy.securityservice.exception;

import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
public class InvalidDataException extends RuntimeException{
    private final List<Map<String, String>> errors;
    public InvalidDataException(List<Map<String, String>> errors){
        super("Invalid data");
        this.errors = errors;
    }
}
