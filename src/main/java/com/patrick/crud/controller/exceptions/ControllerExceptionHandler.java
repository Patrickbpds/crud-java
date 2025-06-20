package com.patrick.crud.controller.exceptions;

import com.patrick.crud.models.exceptions.Error;
import com.patrick.crud.models.exceptions.ResourceNotFoundExceptions;
import com.patrick.crud.models.exceptions.ValidationException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.*;

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

    @ExceptionHandler(DataIntegrityViolationException.class)
    ResponseEntity<Error> handleDataIntegrityViolationException(
            final DataIntegrityViolationException ex, final HttpServletRequest request)
    {
        return ResponseEntity.status(CONFLICT).body(
                Error.builder()
                        .timestamp(now())
                        .status(CONFLICT.value())
                        .error(CONFLICT.getReasonPhrase())
                        .message(ex.getMessage())
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

    @ExceptionHandler(BadCredentialsException.class)
    ResponseEntity<Error> handleBadCredentialsException(
            final BadCredentialsException ex, final HttpServletRequest request) {
        return ResponseEntity.status(UNAUTHORIZED).body(
                Error.builder()
                        .timestamp(now())
                        .status(UNAUTHORIZED.value())
                        .error(UNAUTHORIZED.getReasonPhrase())
                        .message("Invalid credentials")
                        .path(request.getRequestURI())
                        .build()
        );
    }

}