package com.gamaza.examples.kafka.dto;

import java.io.Serial;
import java.io.Serializable;

/**
 * DTO for standard responses
 */
public class StandardResponseDto implements Serializable {

    @Serial
    private static final long serialVersionUID = -7279935844917902502L;

    // Private variables
    private String message;

    /**
     * Constructor
     */
    public StandardResponseDto() {
    }

    /**
     * Constructor
     */
    public StandardResponseDto(String message) {
        this.message = message;
    }

    /**
     * Static of method
     */
    public static StandardResponseDto of(String message) {
        return new StandardResponseDto(message);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
