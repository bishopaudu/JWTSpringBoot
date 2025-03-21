package com.testauth.auth.repository;

import com.testauth.auth.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface Repository  extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}
