package com.foro_hub.foro_hub.application.service;

import com.foro_hub.foro_hub.domain.repository.CommentRepository;

public class DeleteCommentService {

    private final CommentRepository commentRepository;

    public DeleteCommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }       
}