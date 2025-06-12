package com.patrick.crud.controller;

import com.patrick.crud.models.exceptions.Error;
import com.patrick.crud.models.requests.CreateUserRequest;
import com.patrick.crud.models.requests.LoginRequest;
import com.patrick.crud.models.requests.TokenResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "AuthController", description = "Authentication Endpoints")
@RequestMapping("/auth")
public interface AuthResource {

    @Operation(summary = "User login to receive the authentication token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully authenticated"),
            @ApiResponse(
                    responseCode = "401", description = "Invalid credentials",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Error.class)
                    )),
    })
    @PostMapping("/sign-in")
    ResponseEntity<TokenResponse> login (
        @Parameter(description = "email and password for authentication", required = true)
        @Valid @RequestBody LoginRequest loginRequest);



    @Operation(summary = "Register a new user to access the authentication token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "User created successfully"),
            @ApiResponse(
                    responseCode = "400", description = "Wrong or missing information",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Error.class)
                    )),
            @ApiResponse(
                    responseCode = "409", description = "Email already in use",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Error.class)
                    )),

    })
    @PutMapping("/sign-up")
    ResponseEntity<Void> register (
        @Parameter(description = "Signup a new user given his email and password", required = true)
        @Valid @RequestBody CreateUserRequest createUserRequest);
}
