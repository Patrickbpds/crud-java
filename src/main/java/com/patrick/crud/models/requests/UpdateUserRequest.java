package com.patrick.crud.models.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.With;

@With
public record UpdateUserRequest(
        @Schema(description = "User name", example = "John Doe")
        @Size(min = 3, max = 80, message = "Name must be between 3 and 80 characters")
        String name,

        @Schema(description = "User email", example = "johndoe@example.com")
        @Email(message = "Invalid email")
        @Size(min = 6, max = 80, message = "Email must be between 6 and 50 characters")
        String email,

        @Schema(description = "User password", example = "@Password123")
        @Size(min = 6, max = 50, message = "Password must be between 6 and 50 characters")
        String password
) {

}
