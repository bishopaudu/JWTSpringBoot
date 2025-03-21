package com.testauth.auth.controllers;

import com.testauth.auth.AuthApplication;
import com.testauth.auth.models.User;
import com.testauth.auth.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/me")
@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/currentuser")
    public ResponseEntity<User> getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || auth.getPrincipal() == null) {
            return ResponseEntity.status(401).build(); // Unauthorized
        }
        System.out.println("Authentication Object: " + auth);
        System.out.println("Principal: " + auth.getPrincipal());
        System.out.println("Principal Class: " + auth.getPrincipal().getClass());

        User currentUser = (User) auth.getPrincipal();
        return ResponseEntity.ok(currentUser);
    }

    @GetMapping("/")
    public ResponseEntity<List<User>> allUsers() {
        List <User> users = userService.getAllUsers();

        return ResponseEntity.ok(users);
    }
}
