package com.foro_hub.foro_hub.application.service;

import com.foro_hub.domain.model.Comment;
import com.foro_hub.domain.repository.CommentRepository;

import java.util.Optional;

public class UpdateCommentService {

    private final CommentRepository commentRepository;

    public UpdateCommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Comment updateComment(Long commentId, String newContent) {
        Optional<Comment> optionalComment = commentRepository.findById(commentId);
        if (optionalComment.isEmpty()) {
            throw new IllegalArgumentException("Comentario no encontrado");
        }
        Comment comment = optionalComment.get();
        comment.setContent(newContent);
        return commentRepository.save(comment);
    }
}