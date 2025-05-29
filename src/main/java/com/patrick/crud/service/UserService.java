package com.patrick.crud.service;

import com.patrick.crud.entity.User;
import com.patrick.crud.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User findById(final Long id) {
        return userRepository.findById(id).orElse(null);
    }
}
