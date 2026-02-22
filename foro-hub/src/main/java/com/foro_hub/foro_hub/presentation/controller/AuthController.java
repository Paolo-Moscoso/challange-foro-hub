package com.foro_hub.foro_hub.presentation.controller;

import com.foro_hub.foro_hub.application.service.RegisterUserService;
import com.foro_hub.foro_hub.domain.model.User;
import com.foro_hub.foro_hub.presentation.dto.UserDTO;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final RegisterUserService registerUserService;

    public AuthController(RegisterUserService registerUserService) {
        this.registerUserService = registerUserService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserDTO userDTO) {
        try {
            User user = registerUserService.register(
                userDTO.getUsername(),
                userDTO.getEmail(),
                userDTO.getPassword()
            );

            return ResponseEntity.status(HttpStatus.CREATED).body(
                "User registered successfully with ID: " + user.getId()
            );
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Server error");
        }
    }
}