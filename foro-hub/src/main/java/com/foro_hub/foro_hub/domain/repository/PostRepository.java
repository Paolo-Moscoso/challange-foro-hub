package com.foro_hub.foro_hub.domain.repository;

import com.foro_hub.domain.model.Post;
import java.util.List;
import java.util.Optional;

public interface PostRepository {
    Post save(Post post);
    Optional<Post> findById(Long id);
    List<Post> findAll();
    void deleteById(Long id);
}