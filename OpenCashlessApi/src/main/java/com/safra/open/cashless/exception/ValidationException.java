package com.safra.open.cashless.exception;

import com.safra.open.cashless.enums.ValidationErrorsEnum;

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
