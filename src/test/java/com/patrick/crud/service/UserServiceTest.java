package com.patrick.crud.service;

import com.patrick.crud.entity.User;
import com.patrick.crud.mapper.UserMapper;
import com.patrick.crud.models.responses.UserResponse;
import com.patrick.crud.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @Test
    void whenCallFindByPublicId_thenReturnUserResponse() {
        // Given
        User mockUser = new User();
        UserResponse mockResponse = mock(UserResponse.class);

        when(userRepository.findByPublicId(anyString())).thenReturn(Optional.of(mockUser));
        when(userMapper.fromEntity(any(User.class))).thenReturn(mockResponse);

        // When
        UserResponse result = userService.findById("123456");

        // Then
        Assertions.assertNotNull(result);
        Assertions.assertEquals(UserResponse.class, result.getClass());

        verify(userRepository, times(1)).findByPublicId("123456");
        verify(userMapper, times(1)).fromEntity(mockUser);
    }
}