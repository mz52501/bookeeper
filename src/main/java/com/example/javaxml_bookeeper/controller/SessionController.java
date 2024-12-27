package com.example.javaxml_bookeeper.controller;

import com.example.javaxml_bookeeper.dto.LoginRequest;
import com.example.javaxml_bookeeper.models.User;
import com.example.javaxml_bookeeper.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class SessionController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        Optional<User> userOpt = userRepository.findByLogin(loginRequest.getLogin());

        if (userOpt.isPresent() && userOpt.get().getHashPassword().equals(loginRequest.getHash_password())) {
            User user = userOpt.get();
            System.out.println("Login successful for user: " + user.getLogin()); // Debug log
            return ResponseEntity.ok(user); // Renvoie les données utilisateur
        }
        System.out.println("Login failed: invalid credentials."); // Debug log
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid login or password");
    }

}
