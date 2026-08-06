package com.juliodalpiaz.reviewhub.exception;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<Map<String, String>> handleNotFound(ResourceNotFoundException e){
    return new ResponseEntity<>(Map.of("message", e.getMessage()), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Map<String, String>> handleValidation(MethodArgumentNotValidException e){
    String message = e.getBindingResult().getFieldErrors().stream()
      .map(fe -> fe.getField() + ": " + fe.getDefaultMessage())
      .collect(Collectors.joining(", "));
    return new ResponseEntity<>(Map.of("message", message), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(DataIntegrityViolationException.class)
  public ResponseEntity<Map<String, String>> handleViolation(DataIntegrityViolationException e){
    // Check if this is a duplicate/unique key violation
    if (isDuplicateKeyViolation(e)) {
      return new ResponseEntity<>(Map.of("message", "Duplicate value: resource already exists"), HttpStatus.CONFLICT);
    }
    // Other integrity violations (NOT NULL, foreign key, check constraints, etc.)
    return new ResponseEntity<>(Map.of("message", "Data integrity violation"), HttpStatus.BAD_REQUEST);
  }

  private boolean isDuplicateKeyViolation(DataIntegrityViolationException e) {
    Throwable cause = e.getCause();
    while (cause != null) {
      String message = cause.getMessage();
      if (message != null) {
        String lowerMessage = message.toLowerCase();
        // Check for common duplicate/unique key indicators
        if (lowerMessage.contains("duplicate") ||
            lowerMessage.contains("unique constraint") ||
            lowerMessage.contains("unique index") ||
            lowerMessage.contains("constraint violation")) {
          return true;
        }
      }
      cause = cause.getCause();
    }
    return false;
  }
}
