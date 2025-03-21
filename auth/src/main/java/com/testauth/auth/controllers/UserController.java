package com.testauth.auth.controllers;

import com.testauth.auth.AuthApplication;
import com.testauth.auth.dto.UserUpdateDTO;
import com.testauth.auth.models.User;
import com.testauth.auth.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

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

       /* if (auth == null || auth.getPrincipal() == null) {
            return ResponseEntity.status(401).build(); // Unauthorized
        }
        User currentUser = (User) auth.getPrincipal();*/
        return ResponseEntity.ok(userService.getCurrentUser());
    }

    @GetMapping("/")
    public ResponseEntity<List<User>> allUsers() {
        List <User> users = userService.getAllUsers();

        return ResponseEntity.ok(users);
    }

    @PatchMapping("/update")
    public ResponseEntity<User> updateUser(@RequestBody UserUpdateDTO userUpdateDTO){
        User currentUser = userService.getCurrentUser();
        User updatedUser = userService.updateUser(currentUser.getId(),userUpdateDTO);
        return ResponseEntity.ok(updatedUser);

    }
}
