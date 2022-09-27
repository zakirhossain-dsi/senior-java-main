package at.wirecube.examples.products.application.exception;

import lombok.Getter;

@Getter
public class EnumValidationException extends RuntimeException {
    private final String message;

    public EnumValidationException(String message) {
        super(message);
        this.message = message;
    }

    public EnumValidationException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
    }
}
