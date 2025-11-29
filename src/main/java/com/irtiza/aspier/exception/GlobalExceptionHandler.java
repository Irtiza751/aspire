package com.irtiza.aspier.exception;

import com.irtiza.aspier.dto.ErrorResponse;
import com.irtiza.aspier.dto.InternalServerErrorResponse;
import com.irtiza.aspier.dto.ValidationErrorResponse;
import jakarta.validation.ValidationException;
import org.jspecify.annotations.NullMarked;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @NullMarked
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ValidationErrorResponse> handleValidationError(MethodArgumentNotValidException exception) {
        ValidationErrorResponse error = new ValidationErrorResponse();
        var fields = error.getFields();

        error.setMessage("Validation error");
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setTimestamps(System.currentTimeMillis());

        exception.getAllErrors().forEach(e -> {
            String field = e.getObjectName();
            String message = e.getDefaultMessage();

            fields.put(field, message);
        });

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @NullMarked
    @ExceptionHandler({ResponseStatusException.class, ValidationException.class, IllegalArgumentException.class})
    public ResponseEntity<ErrorResponse> handleClientError(Exception exception) {
        ErrorResponse error = new ErrorResponse();
        error.setMessage(exception.getMessage());
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setTimestamps(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @NullMarked
    @ExceptionHandler
    public ResponseEntity<InternalServerErrorResponse> handleServerError(Exception exception) {
        InternalServerErrorResponse error = new InternalServerErrorResponse();
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.setMessage(exception.getMessage());
        error.setTimestamps(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
