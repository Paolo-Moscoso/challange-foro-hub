package com.foro_hub.foro_hub.application.service;

import com.foro_hub.foro_hub.foro_hub.domain.model.Comment;
import com.foro_hub.foro_hub.foro_hub.domain.repository.CommentRepository

import java.util.Optional;

public class DeleteCommentService {

    private final CommentRepository commentRepository;

    public Delete(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Comment deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }       
}