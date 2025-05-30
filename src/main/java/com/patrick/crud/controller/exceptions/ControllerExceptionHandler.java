package com.patrick.crud.controller.exceptions;

import com.patrick.crud.models.exceptions.ResourceNotFoundExceptions;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static java.time.LocalDateTime.now;
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
                        .message(ex.getMessage())
                        .path(request.getRequestURI())
                        .build()
        );
    }
}