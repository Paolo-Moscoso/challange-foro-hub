package com.foro_hub.foro_hub.presentation.dto;

public class CommentDTO {
    private String content;
    private Long authorId;
    private Long postId;

    public CommentDTO() {}

    public CommentDTO(String content, Long authorId, Long postId) {
        this.content = content;
        this.authorId = authorId;
        this.postId = postId;
    }

    // Getters y setters
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public Long getAuthorId() { return authorId; }
    public void setAuthorId(Long authorId) { this.authorId = authorId; }

    public Long getPostId() { return postId; }
    public void setPostId(Long postId) { this.postId = postId; }
}