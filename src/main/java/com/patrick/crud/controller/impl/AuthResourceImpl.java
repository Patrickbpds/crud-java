package com.patrick.crud.controller.impl;

import com.patrick.crud.controller.AuthResource;
import com.patrick.crud.models.requests.CreateUserRequest;
import com.patrick.crud.models.requests.LoginRequest;
import com.patrick.crud.models.responses.TokenResponse;
import com.patrick.crud.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AuthResourceImpl implements AuthResource {

    private final AuthService authService;

    @Override
    public ResponseEntity<TokenResponse> login(@Valid LoginRequest loginRequest) {
        TokenResponse token = authService.login(loginRequest);
        return ResponseEntity.ok(token);
    }

    @Override
    public ResponseEntity<CreateUserRequest> register(@Valid CreateUserRequest createUserRequest){
        authService.register(createUserRequest);
        return ResponseEntity.status(204).build();
    }

}
