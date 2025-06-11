package com.patrick.crud.models.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {

    @NotBlank(message = "Email required")
    @Email(message = "Invalid email format")
    @Schema(example = "patrick@example.com")
    private String email;

    @NotBlank(message = "Password required")
    @Schema(example = "123456")
    private String password;
}
