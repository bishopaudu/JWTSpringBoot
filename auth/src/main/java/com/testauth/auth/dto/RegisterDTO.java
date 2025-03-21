package com.testauth.auth.dto;

import lombok.Data;

@Data
public class RegisterDTO {
    private String name;
    private String email;
    private String password;
    private String occupation;
}
