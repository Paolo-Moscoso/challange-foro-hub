package com.foro_hub.foro_hub.infrastructure.persistence;

import com.foro_hub.foro_hub.domain.model.Post;
import com.foro_hub.foro_hub.domain.repository.PostRepository;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@repository
public class JpaPostRepository implements PostRepository {

    private final SpringDataPostRepository springDataPostRepository;

    public JpaPostRepository(SpringDataPostRepository springDataPostRepository) {
        this.springDataPostRepository = springDataPostRepository;
    }

    @Override
    public Post save(Post post) {
        PostEntity entity = new PostEntity();
        entity.setTitle(post.getTitle());
        entity.setContent(post.getContent());
        // Convertir autor
        UserEntity authorEntity = new UserEntity();
        authorEntity.setId(post.getAuthor().getId());
        entity.setAuthor(authorEntity);

        PostEntity saved = springDataPostRepository.save(entity);

        Post result = new Post(
            saved.getTitle(),
            saved.getContent(),
            post.getAuthor()
        );
        result.setId(saved.getId());
        return result;
    }

    @Override
    public Optional<Post> findById(Long id) {
        return springDataPostRepository.findById(id)
            .map(entity -> {
                Post post = new Post(
                    entity.getTitle(),
                    entity.getContent(),
                    new com.foro_hub.domain.model.User(
                        entity.getAuthor().getUsername(),
                        entity.getAuthor().getEmail(),
                        entity.getAuthor().getPassword(),
                        null
                    )
                );
                post.setId(entity.getId());
                return post;
            });
    }

    @Override
    public List<Post> findAll() {
        return springDataPostRepository.findAll().stream()
            .map(entity -> {
                Post post = new Post(
                    entity.getTitle(),
                    entity.getContent(),
                    new com.foro_hub.domain.model.User(
                        entity.getAuthor().getUsername(),
                        entity.getAuthor().getEmail(),
                        entity.getAuthor().getPassword(),
                        null
                    )
                );
                post.setId(entity.getId());
                return post;
            })
            .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        springDataPostRepository.deleteById(id);
    }

    interface SpringDataPostRepository extends org.springframework.data.jpa.repository.JpaRepository<PostEntity, Long> {}
}