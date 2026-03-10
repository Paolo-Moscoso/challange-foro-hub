package com.foro_hub.foro_hub.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SpringDataCommentRepository
        extends JpaRepository<CommentEntity, Long> {

    List<CommentEntity> findByPostId(Long postId);
}