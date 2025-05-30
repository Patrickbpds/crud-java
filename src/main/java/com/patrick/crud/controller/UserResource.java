package com.patrick.crud.controller;

import com.patrick.crud.models.responses.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/users")
public interface UserResource {

    @GetMapping("/{id}")
    ResponseEntity<UserResponse> findById(@PathVariable(name="id") final Long id);


}
