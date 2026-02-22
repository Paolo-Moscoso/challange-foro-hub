package com.foro_hub.foro_hub.infrastructure.config;

import com.foro_hub.foro_hub.application.service.RegisterUserService;
import com.foro_hub.foro_hub.domain.repository.UserRepository;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public RegisterUserService registerUserService(UserRepository userRepository) {
        return new RegisterUserService(userRepository);
    }
}