package com.testauth.auth.dto;

import lombok.Data;

@Data
public class UserUpdateDTO {
    private String name;
    private String occupation;
    private String email;
}
