package com.patrick.crud.controller.impl;

import com.patrick.crud.controller.UserResource;
import com.patrick.crud.models.requests.CreateUserRequest;
import com.patrick.crud.models.requests.DeleteUserRequest;
import com.patrick.crud.models.requests.UpdateUserRequest;
import com.patrick.crud.models.responses.UserResponse;
import com.patrick.crud.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserResourceImpl implements UserResource {

    private final UserService userService;

    @Override
    public ResponseEntity<UserResponse> findById(String publicId) {
        return ResponseEntity.ok().body(userService.findById(publicId));
    }

    @Override
    public ResponseEntity<Void> save(final CreateUserRequest createUserRequest) {
        userService.save(createUserRequest);
        return ResponseEntity.status(HttpStatus.CREATED.value()).build();
    }

    @Override
    public ResponseEntity<List<UserResponse>> findAll() {
        return ResponseEntity.ok().body(userService.findAll());
    }

    @Override
    public ResponseEntity<UserResponse> update(final String publicId, final UpdateUserRequest updateUserRequest) {
        return ResponseEntity.ok().body(userService.update(publicId, updateUserRequest));
    }

    @Override
    public ResponseEntity<UserResponse> delete(final String email, final DeleteUserRequest deleteUserRequest) {
        return ResponseEntity.ok().body(userService.delete(email, deleteUserRequest));
    }
}
