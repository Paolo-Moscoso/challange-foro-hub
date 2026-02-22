package com.foro_hub.foro_hub.application.service;

import com.foro_hub.foro_hub.domain.model.Role;
import com.foro_hub.foro_hub.domain.model.User;
import com.foro_hub.foro_hub.domain.repository.UserRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class RegisterUserService {

    private final UserRepository userRepository;

    public RegisterUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User register(String username, String email, String password) {
        // Validación simple: email no repetido
        Optional<User> existingUser = userRepository.findByEmail(email);
        if (existingUser.isPresent()) {
            throw new IllegalArgumentException("Email already in use");
        }

        // Rol USER por defecto
        Set<Role> roles = new HashSet<>();
        roles.add(new Role("USER"));

        User user = new User(username, email, password, roles);
        return userRepository.save(user);
    }
}