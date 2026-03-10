package com.foro_hub.foro_hub.presentation.controller;

import com.foro_hub.foro_hub.application.service.LoginUserService;
import com.foro_hub.foro_hub.domain.model.User;
import com.foro_hub.foro_hub.infrastructure.security.JwtProvider;
import com.foro_hub.foro_hub.presentation.dto.LoginRequestDTO;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class LoginController {

    private final LoginUserService loginUserService;
    private final JwtProvider jwtProvider;

    public LoginController(LoginUserService loginUserService,
                           JwtProvider jwtProvider) {
        this.loginUserService = loginUserService;
        this.jwtProvider = jwtProvider;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO request) {

        User user = loginUserService.login(
                request.getEmail(),
                request.getPassword()
        );

        String token = jwtProvider.generateToken(user.getEmail());

        return ResponseEntity.ok(token);
    }
}