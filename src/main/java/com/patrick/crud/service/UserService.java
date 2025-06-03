package com.patrick.crud.service;

import com.patrick.crud.mapper.UserMapper;
import com.patrick.crud.models.exceptions.ResourceNotFoundExceptions;
import com.patrick.crud.models.responses.UserResponse;
import com.patrick.crud.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserResponse findById(final String publicId) {
        return userMapper.fromEntity(
                userRepository.findByPublicId(publicId).orElseThrow(() ->
                        new ResourceNotFoundExceptions("Object not found with id: " + publicId +
                                " Type: "+ UserResponse.class.getSimpleName()))
        );
    }
}
