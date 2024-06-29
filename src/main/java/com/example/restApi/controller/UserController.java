package com.example.restApi.controller;

import com.example.restApi.entities.User;
import com.example.restApi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return new ResponseEntity<>(
                userService.getAllUsers(),
                HttpStatus.OK).getBody();
    }

    @PostMapping("/addUser")
    public User addUser(@RequestBody User user) {
        return new ResponseEntity<>(
                userService.createUser(user),
                HttpStatus.CREATED).getBody();
    }
}
