package com.gamaza.examples.springkafka.config.exception;

import com.gamaza.examples.springkafka.dto.ProblemDetailDto;
import com.gamaza.examples.springkafka.util.ExceptionUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

/**
 * Global Exception Handler
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    // Global constants
    private static final String DESCRIPTION = "description";

    @ExceptionHandler(value = Exception.class)
    ResponseEntity<ProblemDetailDto> handleException(Exception exception) {
        return ResponseEntity.internalServerError().body(
                ProblemDetailDto.ProblemDetailDtoBuilder
                        .newInstance(INTERNAL_SERVER_ERROR, ExceptionUtils.getCauseOrDefaultMessage(exception))
                        .withTitle("Internal server error")
                        .withProperty(DESCRIPTION, "Server response with an Internal Error")
                        .build()
        );
    }

}
