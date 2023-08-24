package com.advertisement.clicklog.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.format.DateTimeParseException;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(DateTimeParseException.class)
  public ResponseEntity<ErrorResponse> handleDateTimeParseException(DateTimeParseException ex) {
    final var errorResponse =
        ErrorResponse.Builder.newBuilder()
            .withStatus(HttpStatus.BAD_REQUEST.value())
            .withError(HttpStatus.BAD_REQUEST.getReasonPhrase())
            .withMessage("Invalid date format. Please use yyyy-MM-dd HH:mm:ss format.")
            .build();

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
  }
}
