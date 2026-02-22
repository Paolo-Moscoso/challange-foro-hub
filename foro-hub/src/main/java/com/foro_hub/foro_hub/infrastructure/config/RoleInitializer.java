package com.foro_hub.foro_hub.infrastructure.config;

import com.foro_hub.foro_hub.infrastructure.persistence.RoleEntity;
import com.foro_hub.foro_hub.infrastructure.persistence.UserEntity;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashSet;

@Component
public class RoleInitializer implements CommandLineRunner {

    private final SpringDataRoleRepository roleRepository;
    private final SpringDataUserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public RoleInitializer(SpringDataRoleRepository roleRepository,
                           SpringDataUserRepository userRepository,
                           BCryptPasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        // Crear roles si no existen
        if (roleRepository.count() == 0) {
            RoleEntity userRole = roleRepository.save(new RoleEntity("USER"));
            RoleEntity adminRole = roleRepository.save(new RoleEntity("ADMIN"));
            System.out.println("Roles USER y ADMIN creados");

            // Crear usuario ADMIN
            UserEntity admin = new UserEntity(
                    "admin",
                    "admin@foro.com",
                    passwordEncoder.encode("admin123"),
                    new HashSet<>(Collections.singletonList(adminRole))
            );
            userRepository.save(admin);

            // Crear usuario USER
            UserEntity user = new UserEntity(
                    "usuario",
                    "user@foro.com",
                    passwordEncoder.encode("user123"),
                    new HashSet<>(Collections.singletonList(userRole))
            );
            userRepository.save(user);

            System.out.println("Usuario ADMIN y USER creados");
        }
    }

    interface SpringDataRoleRepository extends org.springframework.data.jpa.repository.JpaRepository<RoleEntity, Long> {
        RoleEntity findByName(String name);
    }

    interface SpringDataUserRepository extends org.springframework.data.jpa.repository.JpaRepository<UserEntity, Long> {
        UserEntity findByEmail(String email);
    }
}