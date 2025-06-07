package com.patrick.crud.repository;

import com.patrick.crud.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByPublicId(final String publicId);

    Optional<User> findByEmail(final String email);
}