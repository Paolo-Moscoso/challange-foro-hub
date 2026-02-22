package com.foro_hub.foro_hub.infrastructure.config;

import com.foro_hub.foro_hub.infrastructure.persistence.JpaUserRepository;
import com.foro_hub.foro_hub.infrastructure.persistence.RoleEntity;
import com.foro_hub.foro_hub.infrastructure.persistence.JpaUserRepository.SpringDataUserRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class RoleInitializer implements CommandLineRunner {

    private final SpringDataRoleRepository roleRepository;

    public RoleInitializer(SpringDataRoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (roleRepository.count() == 0) {
            roleRepository.save(new RoleEntity("USER"));
            roleRepository.save(new RoleEntity("ADMIN"));
            System.out.println("Roles USER y ADMIN creados");
        }
    }

    interface SpringDataRoleRepository extends org.springframework.data.jpa.repository.JpaRepository<RoleEntity, Long> {
        RoleEntity findByName(String name);
    }
}