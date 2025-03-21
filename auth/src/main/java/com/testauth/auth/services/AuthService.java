package com.testauth.auth.services;

import com.testauth.auth.dto.LoginDTO;
import com.testauth.auth.dto.RegisterDTO;
import com.testauth.auth.models.User;
import com.testauth.auth.repository.Repository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final Repository repository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    public AuthService(Repository repository, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }

    public User signup(RegisterDTO registerDTO){
        User user = new User();
        user.setName(registerDTO.getName());
        user.setEmail(registerDTO.getEmail());
        user.setOccupation(registerDTO.getOccupation());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        return repository.save(user);
    }

    public User authenticate(LoginDTO loginDTO){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDTO.getEmail(),
                        loginDTO.getPassword()
                )
        );
                return repository.findByEmail(loginDTO.getEmail())
                        .orElseThrow();

    }
}
