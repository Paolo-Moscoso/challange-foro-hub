package com.foro_hub.foro_hub.application.service;

import com.foro_hub.domain.model.Post;
import com.foro_hub.domain.model.User;
import com.foro_hub.domain.repository.PostRepository;

public class CreatePostService {

    private final PostRepository postRepository;

    public CreatePostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post createPost(String title, String content, User author) {
        Post post = new Post(title, content, author);
        return postRepository.save(post);
    }
}