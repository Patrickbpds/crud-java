package com.patrick.crud.models.requests;

import lombok.With;

@With
public record CreateUserRequest(
        String name,
        String email,
        String password
) {

}
