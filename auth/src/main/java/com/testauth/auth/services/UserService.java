package com.testauth.auth.services;


import com.testauth.auth.dto.UserUpdateDTO;
import com.testauth.auth.models.User;
import com.testauth.auth.repository.Repository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final Repository repository;

    public UserService(Repository repository) {
        this.repository = repository;
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        users.addAll(repository.findAll());
        return users;
    }

    public User getCurrentUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
       /* if (auth == null || auth.getPrincipal() == null) {
            return ResponseEntity.status(401).build(); // Unauthorized
        }*/
        User currentUser = (User) auth.getPrincipal();
        return  currentUser;
    }

    public User updateUser(Integer id, UserUpdateDTO userUpdateDTO){
        User user  = repository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("user not found"));

        if (user.getName() != null){
            user.setName(userUpdateDTO.getName());
        }
        if (user.getEmail() != null){
            user.setEmail(userUpdateDTO.getEmail());
        }

        if (user.getOccupation() != null){
            user.setOccupation(userUpdateDTO.getOccupation());
        }

        return  repository.save(user);
    }

}
