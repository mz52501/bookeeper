package com.example.javaxml_bookeeper.controller;

import com.example.javaxml_bookeeper.repository.UserRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

}
