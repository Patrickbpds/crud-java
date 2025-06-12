package com.patrick.crud.models.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.With;

@With
public record LoginRequest (

    @NotBlank(message = "Email required")
    @Email(message = "Invalid email format")
    @Schema(example = "patrick@example.com")
    String email,

    @NotBlank(message = "Password required")
    @Schema(example = "123456")
    String password
){}
