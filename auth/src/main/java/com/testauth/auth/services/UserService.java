package com.testauth.auth.services;


import com.testauth.auth.models.User;
import com.testauth.auth.repository.Repository;
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
}
