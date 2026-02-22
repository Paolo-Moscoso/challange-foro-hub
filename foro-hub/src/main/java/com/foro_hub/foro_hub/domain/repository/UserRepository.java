package com.foro_hub.foro_hub.domain.repository;

import com.foro_hub.foro_hub.domain.model.User;

import java.util.Optional;

public interface UserRepository {
    User save(User user);
    Optional<User> findByEmail(String email);
    Optional<User> findById(Long id);
}