package com.example.football_management.exception;

import org.springframework.validation.BindingResult;

public class ValidationException extends Throwable {
    public ValidationException(String validationFailed, BindingResult bindingResult) {
    }
}
