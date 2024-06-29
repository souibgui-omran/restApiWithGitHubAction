package com.example.restApi.service;

import com.example.restApi.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    User createUser(User user);

    List<User> getAllUsers();
}
