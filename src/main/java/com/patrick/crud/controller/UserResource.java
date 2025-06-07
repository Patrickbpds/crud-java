package com.patrick.crud.controller;

import com.patrick.crud.controller.exceptions.Error;
import com.patrick.crud.models.requests.CreateUserRequest;
import com.patrick.crud.models.responses.UserResponse;
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

@Tag(name="UserResource", description = "Resource responsible for user operations")
@RequestMapping("/api/users")
public interface UserResource {

    @Operation(summary = "Find user by id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "404", description = "User not found",
                    content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = Error.class)
                    )),
            @ApiResponse(
                    responseCode = "500", description = "Internal server error",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Error.class)
                    )),

    })
    @GetMapping("/{publicId}")
    ResponseEntity<UserResponse> findById(
            @Parameter(description = "Public ID of the user to be found",
                    required = true, example = "180302")
            @PathVariable(name="publicId") final String publicId);

    @Operation(summary = "Save a new user")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201", description = "User created successfully"),
            @ApiResponse(
                    responseCode = "400", description = "Bad request",
                    content = @Content(
                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                        schema = @Schema(implementation = Error.class)
                    )),
            @ApiResponse(
                    responseCode = "500", description = "Internal server error",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Error.class)
                    ))
    })
    @PostMapping
    ResponseEntity<Void> save(
           @Valid @RequestBody final CreateUserRequest createUserRequest);
}
