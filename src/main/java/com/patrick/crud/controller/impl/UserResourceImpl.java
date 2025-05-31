package com.patrick.crud.controller.impl;

import com.patrick.crud.controller.UserResource;
import com.patrick.crud.models.responses.UserResponse;
import com.patrick.crud.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserResourceImpl implements UserResource {

    private final UserService userService;

    @Override
    public ResponseEntity<UserResponse> findById(Long publicId) {
        return ResponseEntity.ok().body(userService.findById(publicId));
    }
}
