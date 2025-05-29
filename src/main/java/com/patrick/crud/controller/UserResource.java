package com.patrick.crud.controller;

import com.patrick.crud.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/users")
public interface UserResource {

    @GetMapping("/{id}")
    ResponseEntity<User> findById(@PathVariable(name="id") final Long id);


}
