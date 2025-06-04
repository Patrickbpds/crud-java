package com.patrick.crud.models.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ResourceNotFoundExceptions extends ResponseStatusException {

    public ResourceNotFoundExceptions(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}
