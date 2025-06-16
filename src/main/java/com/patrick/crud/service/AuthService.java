package com.patrick.crud.service;

import com.patrick.crud.mapper.UserMapper;
import com.patrick.crud.models.requests.CreateUserRequest;
import com.patrick.crud.models.requests.LoginRequest;
import com.patrick.crud.models.responses.TokenResponse;
import com.patrick.crud.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * Authenticates the user and returns the JWT token.
     * @throws BadCredentialsException if invalid credentials
     */

    public TokenResponse login(LoginRequest loginRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.email(),
                            loginRequest.password()
                        )
                );

            String token = jwtService.generateToken(loginRequest.email());
            log.info("Successful login to {}", loginRequest.email());
            return new TokenResponse(token);

    } catch (AuthenticationException ex) {
        log.warn("Login failure for {}: {}", loginRequest.email(), ex.getMessage());
        throw new BadCredentialsException("Invalid credentials");
    }
}


    public void register(CreateUserRequest createUserRequest) {
        if (userRepository.findByEmail(createUserRequest.email()).isPresent()) {
            throw new DataIntegrityViolationException("Email already registered");
        }
        var user = userMapper.fromRequest(createUserRequest);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        log.info("User {} successfully registered", createUserRequest.email());
        }
}