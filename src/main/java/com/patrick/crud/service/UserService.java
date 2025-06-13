package com.patrick.crud.service;

import com.patrick.crud.entity.User;
import com.patrick.crud.mapper.UserMapper;
import com.patrick.crud.models.exceptions.ResourceNotFoundExceptions;
import com.patrick.crud.models.requests.CreateUserRequest;
import com.patrick.crud.models.requests.DeleteUserRequest;
import com.patrick.crud.models.requests.UpdateUserRequest;
import com.patrick.crud.models.responses.UserResponse;
import com.patrick.crud.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserResponse findById(final String publicId) {
        return userMapper.fromEntity(find(publicId));
    }

    public void save(CreateUserRequest createUserRequest) {
        verifyEmailAlreadyExists(createUserRequest.email(), null);
        userRepository.save(
                userMapper.fromRequest(createUserRequest)
                        .withPassword(passwordEncoder.encode(createUserRequest.password()))
        );
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


    public UserResponse update(final String publicId, final UpdateUserRequest updateUserRequest) {
            User entity = find(publicId);
            verifyEmailAlreadyExists(updateUserRequest.email(), publicId);
            final var newEntity = userRepository.save(
                    userMapper.update(updateUserRequest, entity)
                            .withPassword(
                                    updateUserRequest.password() != null ?
                                            passwordEncoder.encode(updateUserRequest.password())
                                            : entity.getPassword()));
            return userMapper.fromEntity(newEntity);
        }

    private User find(final String publicId) {
        return userRepository.findByPublicId(publicId).orElseThrow(() ->
                new ResourceNotFoundExceptions("Object not found with id: " + publicId +
                        " Type: "+ UserResponse.class.getSimpleName()));
    }


    public UserResponse delete(final String email, final DeleteUserRequest deleteUserRequest) {
        User user = findByEmail(email);

        if (!deleteUserRequest.isConfirmationValid()) {
            throw new BadCredentialsException("Invalid confirmation text. Must be 'DELETE'");
        }

        if (!passwordEncoder.matches(deleteUserRequest.password(), user.getPassword())) {
            throw new BadCredentialsException("Invalid password for user: " + email);
        }

        userRepository.delete(user);
        return userMapper.fromEntity(user);
    }

    private User findByEmail(final String email) {
        return userRepository.findByEmail(email).orElseThrow(() ->
                new ResourceNotFoundExceptions("User not found with email: " + email +
                        " Type: " + UserResponse.class.getSimpleName()));
    }
}
