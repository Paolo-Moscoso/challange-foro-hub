package com.foro_hub.foro_hub.presentation.controller;


import com.foro_hub.application.service.CreateCommentService;
import com.foro_hub.application.service.ListCommentsService;
import com.foro_hub.application.service.UpdateCommentService;
import com.foro_hub.application.service.DeleteCommentService;
import com.foro_hub.domain.model.Comment;
import com.foro_hub.domain.model.Post;
import com.foro_hub.domain.model.User;
import com.foro_hub.domain.repository.PostRepository;
import com.foro_hub.domain.repository.UserRepository;
import com.foro_hub.presentation.dto.CommentDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private final CreateCommentService createCommentService;
    private final ListCommentsService listCommentsService;
    private final UpdateCommentService updateCommentService;
    private final DeleteCommentService deleteCommentService;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public CommentController(CreateCommentService createCommentService,
                             ListCommentsService listCommentsService,
                             UpdateCommentService updateCommentService,
                             DeleteCommentService deleteCommentService,
                             UserRepository userRepository,
                             PostRepository postRepository) {
        this.createCommentService = createCommentService;
        this.listCommentsService = listCommentsService;
        this.updateCommentService = updateCommentService;
        this.deleteCommentService = deleteCommentService;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createComment(@RequestBody CommentDTO commentDTO) {
        User author = userRepository.findById(commentDTO.getAuthorId()).orElse(null);
        Post post = postRepository.findById(commentDTO.getPostId()).orElse(null);

        if (author == null || post == null) {
            return ResponseEntity.badRequest().body("Usuario o post no encontrado");
        }

        Comment comment = createCommentService.createComment(commentDTO.getContent(), author, post);
        return ResponseEntity.ok("Comentario creado con ID: " + comment.getId());
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<List<Comment>> listComments(@PathVariable Long postId) {
        return ResponseEntity.ok(listCommentsService.listByPost(postId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateComment(@PathVariable Long id, @RequestBody CommentDTO commentDTO) {
        try {
            Comment updated = updateCommentService.updateComment(id, commentDTO.getContent());
            return ResponseEntity.ok(updated);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable Long id) {
        try {
            deleteCommentService.deleteComment(id);
            return ResponseEntity.ok("Comentario eliminado con ID: " + id);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Comentario no encontrado");
        }
    }
}