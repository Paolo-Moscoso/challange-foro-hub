package com.foro_hub.foro_hub.application.service;

import com.foro_hub.foro_hub.domain.model.User;
import com.foro_hub.foro_hub.domain.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginUserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public LoginUserService(UserRepository userRepository,
                            PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User login(String email, String password) {

        Optional<User> userOpt = userRepository.findByEmail(email);

        if (userOpt.isEmpty()) {
            throw new RuntimeException("Usuario no encontrado");
        }

        User user = userOpt.get();

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Password incorrecto");
        }

        return user;
    }
}