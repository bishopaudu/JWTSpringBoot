package com.testauth.auth.controllers;

import com.testauth.auth.dto.LoginDTO;
import com.testauth.auth.dto.LoginResponseDTO;
import com.testauth.auth.dto.RegisterDTO;
import com.testauth.auth.models.User;
import com.testauth.auth.services.AuthService;
import com.testauth.auth.services.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;
    private final JwtService jwtService;

    public AuthController(AuthService authService, JwtService jwtService) {
        this.authService = authService;
        this.jwtService = jwtService;
    }

    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody RegisterDTO registerDTO){
        User user = authService.signup(registerDTO);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginDTO loginDTO) {
        User user = authService.authenticate(loginDTO);
        String jwtToken = jwtService.generateToken(user);

        LoginResponseDTO loginResponse = new LoginResponseDTO();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTimeInMinutes());

        return ResponseEntity.ok(loginResponse);
    }
}
