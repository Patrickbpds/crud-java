package com.patrick.crud.controller.exceptions;

import com.patrick.crud.models.exceptions.ResourceNotFoundExceptions;
import com.patrick.crud.models.exceptions.ValidationException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundExceptions.class)
    ResponseEntity<Error> handleResourceNotFoundException(
            final ResourceNotFoundExceptions ex, final HttpServletRequest request)
    {
        return ResponseEntity.status(NOT_FOUND).body(
                Error.builder()
                        .timestamp(now())
                        .status(NOT_FOUND.value())
                        .error(NOT_FOUND.getReasonPhrase())
                        .message(ex.getReason())
                        .path(request.getRequestURI())
                        .build()
        );
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<ValidationException> handleMethodArgumentNotValidException(
            final MethodArgumentNotValidException ex, final HttpServletRequest request)
    {
        var error = ValidationException.builder()
                .timestamp(now())
                .status(BAD_REQUEST.value())
                .error("Validation Exception")
                .message("Exception in validation attributes")
                .path(request.getRequestURI())
                .errors(new ArrayList<>())
                .build();
        for(FieldError fieldError : ex.getBindingResult().getFieldErrors()){
            error.addError(fieldError.getField(), fieldError.getDefaultMessage());
        }

        return ResponseEntity.badRequest().body(error);
    }
}