package com.patrick.crud.models.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.With;

@With
public record DeleteUserRequest(

        @Schema(description = "User password", example = "@Password123")
        @NotBlank(message = "Password cannot be empty")
        @Size(min = 6, max = 50, message = "Password must be between 6 and 50 characters")
        String password,

        @Schema(description = "Confirmation text - must be 'DELETE' to proceed", example = "DELETE")
        @NotBlank(message = "Confirmation password cannot be empty")
        String confirmation
) {
        public boolean isConfirmationValid() {
            return "DELETE".equals(confirmation);
        }
}
