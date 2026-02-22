package com.foro_hub.foro_hub.application.service;

import com.foro_hub.foro_hub.domain.model.Post;
import com.foro_hub.foro_hub.domain.repository.PostRepository;

import java.util.Optional;

public class UpdatePostService {

    private final PostRepository postRepository;

    public UpdatePostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post updatePost(Long postId, String newTitle, String newContent) {
        Optional<Post> optionalPost = postRepository.findById(postId);
        if (optionalPost.isEmpty()) {
            throw new IllegalArgumentException("Post no encontrado");
        }
        Post post = optionalPost.get();
        post.setTitle(newTitle);
        post.setContent(newContent);
        return postRepository.save(post);
    }
}