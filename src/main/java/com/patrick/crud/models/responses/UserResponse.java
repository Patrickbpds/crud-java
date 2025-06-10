package com.patrick.crud.models.responses;

import java.io.Serial;
import java.io.Serializable;

public record UserResponse(
        String publicId,
        String name,
        String email,
        String password
) implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
}
