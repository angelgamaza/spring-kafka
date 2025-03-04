package com.gamaza.examples.springkafka.exception;

import java.io.Serial;

/**
 * Exception for Generic cases
 */
public class GenericRuntimeException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 2441669264602467380L;

    // Class constants
    private static final String MESSAGE = "Unhandled exception with message: %s";

    /**
     * Constructor
     *
     * @param message The exception message
     */
    public GenericRuntimeException(String message) {
        super(
                String.format(MESSAGE, message)
        );
    }

}
