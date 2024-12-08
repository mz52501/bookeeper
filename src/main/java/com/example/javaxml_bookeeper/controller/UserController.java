package com.example.javaxml_bookeeper.controller;

import com.example.javaxml_bookeeper.dto.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/user")
public class UserController {

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
}

