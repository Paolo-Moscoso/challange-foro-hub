package com.foro_hub.foro_hub.application.service;

import com.foro_hub.foro_hub.domain.model.Comment;
import com.foro_hub.foro_hub.domain.repository.CommentRepository;

import java.util.List;

public class ListCommentsService {

    private final CommentRepository commentRepository;

    public ListCommentsService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<Comment> listByPost(Long postId) {
        return commentRepository.findByPostId(postId);
    }

    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}