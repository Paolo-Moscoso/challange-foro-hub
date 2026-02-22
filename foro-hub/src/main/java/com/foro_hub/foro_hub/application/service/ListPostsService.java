package com.foro_hub.foro_hub.application.service;

import com.foro_hub.domain.model.Post;
import com.foro_hub.domain.repository.PostRepository;

import java.util.List;

public class ListPostsService {

    private final PostRepository postRepository;

    public ListPostsService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> listAll() {
        return postRepository.findAll();
    }

    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }
}