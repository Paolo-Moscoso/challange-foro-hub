package com.foro_hub.foro_hub.application.service;

import com.foro_hub.domain.model.Comment;
import com.foro_hub.domain.model.Post;
import com.foro_hub.domain.model.User;
import com.foro_hub.domain.repository.CommentRepository;

public class CreateCommentService {

    private final CommentRepository commentRepository;

    public CreateCommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Comment createComment(String content, User author, Post post) {
        Comment comment = new Comment(content, author, post);
        return commentRepository.save(comment);
    }
}