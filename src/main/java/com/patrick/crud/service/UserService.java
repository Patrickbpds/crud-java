package com.patrick.crud.service;

import com.patrick.crud.entity.User;
import com.patrick.crud.mapper.UserMapper;
import com.patrick.crud.models.exceptions.ResourceNotFoundExceptions;
import com.patrick.crud.models.requests.CreateUserRequest;
import com.patrick.crud.models.responses.UserResponse;
import com.patrick.crud.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserResponse findById(final String publicId) {
        return userMapper.fromEntity(find(publicId));
    }

    public void save(CreateUserRequest createUserRequest) {
        verifyEmailAlreadyExists(createUserRequest.email(), null);
        userRepository.save(userMapper.fromRequest(createUserRequest));
    }

    private void verifyEmailAlreadyExists(final String email, final String publicId) {
        userRepository.findByEmail(email)
                .filter(user -> !user.getPublicId().equals(publicId))
                .ifPresent(user -> {
                    throw new DataIntegrityViolationException("Email already exists: " + email);
                });
        }

    public List<UserResponse> findAll() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::fromEntity)
                .toList();
    }

    private User find(final String publicId) {
        return userRepository.findByPublicId(publicId).orElseThrow(() ->
                new ResourceNotFoundExceptions("Object not found with id: " + publicId +
                        " Type: "+ UserResponse.class.getSimpleName()));
    }
}
