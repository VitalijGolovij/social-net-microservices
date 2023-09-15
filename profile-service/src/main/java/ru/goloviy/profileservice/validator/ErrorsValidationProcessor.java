package ru.goloviy.profileservice.validator;

import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Map;

public interface ErrorsValidationProcessor {
    List<Map<String, String>> processErrors(BindingResult bindingResult);
}
