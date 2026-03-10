package com.foro_hub.foro_hub.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataPostRepository
        extends JpaRepository<PostEntity, Long> {
}