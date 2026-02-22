package com.foro_hub.foro_hub.infrastructure.persistence;

import com.foro_hub.foro_hub.domain.model.Comment;
import com.foro_hub.foro_hub.domain.model.User;
import com.foro_hub.foro_hub.domain.model.Post;
import com.foro_hub.foro_hub.domain.repository.CommentRepository;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class JpaCommentRepository implements CommentRepository {

    private final SpringDataCommentRepository springDataCommentRepository;

    public JpaCommentRepository(SpringDataCommentRepository springDataCommentRepository) {
        this.springDataCommentRepository = springDataCommentRepository;
    }

    @Override
    public Comment save(Comment comment) {
        CommentEntity entity = new CommentEntity();
        entity.setContent(comment.getContent());

        UserEntity authorEntity = new UserEntity();
        authorEntity.setId(comment.getAuthor().getId());
        entity.setAuthor(authorEntity);

        PostEntity postEntity = new PostEntity();
        postEntity.setId(comment.getPost().getId());
        entity.setPost(postEntity);

        CommentEntity saved = springDataCommentRepository.save(entity);

        Comment result = new Comment(
            saved.getContent(),
            comment.getAuthor(),
            comment.getPost()
        );
        result.setId(saved.getId());
        return result;
    }

    @Override
    public Optional<Comment> findById(Long id) {
        return springDataCommentRepository.findById(id)
            .map(entity -> {
                Comment comment = new Comment(
                    entity.getContent(),
                    new User(entity.getAuthor().getUsername(), entity.getAuthor().getEmail(), entity.getAuthor().getPassword(), null),
                    new Post(entity.getPost().getTitle(), entity.getPost().getContent(), null)
                );
                comment.setId(entity.getId());
                return comment;
            });
    }

    @Override
    public List<Comment> findByPostId(Long postId) {
        return springDataCommentRepository.findByPostId(postId).stream()
            .map(entity -> {
                Comment comment = new Comment(
                    entity.getContent(),
                    new User(entity.getAuthor().getUsername(), entity.getAuthor().getEmail(), entity.getAuthor().getPassword(), null),
                    new Post(entity.getPost().getTitle(), entity.getPost().getContent(), null)
                );
                comment.setId(entity.getId());
                return comment;
            })
            .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        springDataCommentRepository.deleteById(id);
    }

    interface SpringDataCommentRepository extends org.springframework.data.jpa.repository.JpaRepository<CommentEntity, Long> {
        List<CommentEntity> findByPostId(Long postId);
    }
}