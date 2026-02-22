package com.foro_hub.foro_hub.presentation.controller;


import com.foro_hub.application.service.CreatePostService;
import com.foro_hub.application.service.ListPostsService;
import com.foro_hub.application.service.UpdatePostService;
import com.foro_hub.application.service.DeletePostService;
import com.foro_hub.domain.model.Post;
import com.foro_hub.domain.model.User;
import com.foro_hub.domain.repository.UserRepository;
import com.foro_hub.presentation.dto.PostDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final CreatePostService createPostService;
    private final ListPostsService listPostsService;
    private final UpdatePostService updatePostService;
    private final DeletePostService deletePostService;
    private final UserRepository userRepository;

    public PostController(CreatePostService createPostService,
                          ListPostsService listPostsService,
                          UpdatePostService updatePostService,
                          DeletePostService deletePostService,
                          UserRepository userRepository) {
        this.createPostService = createPostService;
        this.listPostsService = listPostsService;
        this.updatePostService = updatePostService;
        this.deletePostService = deletePostService;
        this.userRepository = userRepository;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createPost(@RequestBody PostDTO postDTO) {
        return userRepository.findById(postDTO.getAuthorId())
            .map(user -> {
                Post post = createPostService.createPost(postDTO.getTitle(), postDTO.getContent(), user);
                return ResponseEntity.ok("Post creado con ID: " + post.getId());
            })
            .orElse(ResponseEntity.badRequest().body("Usuario no encontrado"));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Post>> listPosts() {
        return ResponseEntity.ok(listPostsService.listAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePost(@PathVariable Long id, @RequestBody PostDTO postDTO) {
        try {
            Post updated = updatePostService.updatePost(id, postDTO.getTitle(), postDTO.getContent());
            return ResponseEntity.ok(updated);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePost(@PathVariable Long id) {
        try {
            deletePostService.deletePost(id);
            return ResponseEntity.ok("Post eliminado con ID: " + id);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Post no encontrado");
        }
    }
}