package com.foro_hub.foro_hub.application.service;

import com.foro_hub.domain.repository.PostRepository;

public class DeletePostService {

    private final PostRepository postRepository;

    public DeletePostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }
}