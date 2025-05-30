package com.patrick.crud.models.responses;

import java.io.Serial;
import java.io.Serializable;

public record UserResponse(
        Long id,
        String name,
        String email
) implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
}
