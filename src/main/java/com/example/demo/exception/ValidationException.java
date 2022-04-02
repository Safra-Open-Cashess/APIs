package la.foton.msf.auth.exception;

import la.foton.msf.auth.enums.ValidationErrorsEnum;

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
