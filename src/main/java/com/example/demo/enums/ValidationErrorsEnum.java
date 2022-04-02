package com.example.demo.enums;

/**
 * Enum de erros para a exceção de validação.
 *
 * @author Vitor Sulzbach
 */
public enum ValidationErrorsEnum {
    NO_HEADER_AUTH("Authorization header can't be empty."),
    AUTH_HEADER_INVALID("Authorization header is invalid.");

    private final String message;

    ValidationErrorsEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
