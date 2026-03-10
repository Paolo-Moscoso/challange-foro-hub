package com.foro_hub.foro_hub.infrastructure.config;

import com.foro_hub.foro_hub.infrastructure.persistence.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataRoleRepository extends JpaRepository<RoleEntity, Long> {
    RoleEntity findByName(String name);
}