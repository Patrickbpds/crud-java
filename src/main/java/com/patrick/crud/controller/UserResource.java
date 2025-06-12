package com.patrick.crud.controller;

import com.patrick.crud.models.exceptions.Error;
import com.patrick.crud.models.requests.CreateUserRequest;
import com.patrick.crud.models.requests.UpdateUserRequest;
import com.patrick.crud.models.responses.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SecurityRequirement(name = "bearerAuth")
@Tag(name="UserResource", description = "Resource responsible for user operations")
@RequestMapping("/api/users")
public interface UserResource {

    @Operation(summary = "Find user by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User found"),
            @ApiResponse(
                    responseCode = "404", description = "User not found",
                    content = @Content(
                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                        schema = @Schema(implementation = Error.class)
                    )),
            @ApiResponse(
                    responseCode = "500", description = "Internal server error",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
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

    @Operation(summary = "Find all users")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "Users found",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = UserResponse.class))
                    )),
            @ApiResponse(
                    responseCode = "500", description = "Internal server error",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Error.class)
                    ))
    })
    @GetMapping
    ResponseEntity<List<UserResponse>> findAll();

    @Operation(summary = "Update User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User updated successfully"),
            @ApiResponse(
                    responseCode = "400", description = "Bad request",
                    content = @Content(
                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                        schema = @Schema(implementation = Error.class)
                    )),
            @ApiResponse(
                    responseCode = "404", description = "User not found",
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
    @PatchMapping("/{publicId}")
    ResponseEntity<UserResponse> update(
            @Parameter(description = "Public ID of the user to be updated",
                    required = true, example = "180302")
            @PathVariable(name="publicId") final String publicId,
            @Valid @RequestBody final UpdateUserRequest updateUserRequest);
}
