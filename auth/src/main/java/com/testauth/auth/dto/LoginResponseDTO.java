package com.testauth.auth.dto;


import lombok.Data;

@Data
public class LoginResponseDTO {
    private String token;
    private long expiresIn;
}