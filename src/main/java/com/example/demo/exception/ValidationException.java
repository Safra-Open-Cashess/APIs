package com.example.demo.exception;

import com.example.demo.enums.ValidationErrorsEnum;

/**
 * Exceção para erros de validação de token.
 *
 * @author Vitor Sulzbach
 */
public class ValidationException extends Exception {

    public ValidationException(ValidationErrorsEnum message) {
        super(message.getMessage());
    }

}
