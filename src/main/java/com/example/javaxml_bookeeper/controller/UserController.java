package com.example.javaxml_bookeeper.controller;

import com.example.javaxml_bookeeper.dto.UserDTO;
import com.example.javaxml_bookeeper.models.User;
import com.example.javaxml_bookeeper.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    private UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/profile")
    public ResponseEntity<UserDTO> getUserProfile() {
        //TODO: Here return the user that is log from database. Temporary one here
        UserDTO user = new UserDTO();
        user.setUserId(1L);
        user.setName("John");
        user.setSurname("Doe");
        user.setDob(LocalDate.of(1990, 1, 15));
        user.setLogin("john.doe");

        return ResponseEntity.ok(user);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        System.out.println("Received user: " + user);
        try {
            // Verify if login already exists
            if (userRepository.findByLogin(user.getLogin()).isPresent()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Login already in use");
            }

            // Save user
            userRepository.save(user);

            return ResponseEntity.ok("User registered successfully");
        } catch (Exception e) {
            e.printStackTrace(); // Print error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        try {
            List<User> users = userRepository.findAll();
            if (users.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(users);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}

