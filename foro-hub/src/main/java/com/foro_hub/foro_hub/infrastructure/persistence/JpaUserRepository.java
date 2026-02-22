package com.foro_hub.foro_hub.infrastructure.persistence;


import com.foro_hub.foro_hub.domain.model.Role;
import com.foro_hub.foro_hub.domain.model.User;
import com.foro_hub.foro_hub.domain.repository.UserRepository;

import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class JpaUserRepository implements UserRepository {

    private final SpringDataUserRepository springDataUserRepository;

    public JpaUserRepository(SpringDataUserRepository springDataUserRepository) {
        this.springDataUserRepository = springDataUserRepository;
    }

    @Override
    public User save(User user) {
        // Convertir User -> UserEntity
        UserEntity entity = new UserEntity();
        entity.setUsername(user.getUsername());
        entity.setEmail(user.getEmail());
        entity.setPassword(user.getPassword());
        entity.setRoles(
            user.getRoles().stream()
                .map(r -> new RoleEntity(r.getName()))
                .collect(Collectors.toSet())
        );

        UserEntity saved = springDataUserRepository.save(entity);

        // Convertir UserEntity -> User (Domain)
        User result = new User(
            saved.getUsername(),
            saved.getEmail(),
            saved.getPassword(),
            saved.getRoles().stream()
                .map(r -> new Role(r.getName()))
                .collect(Collectors.toSet())
        );
        result.setId(saved.getId());
        return result;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return springDataUserRepository.findByEmail(email)
            .map(entity -> {
                User user = new User(
                    entity.getUsername(),
                    entity.getEmail(),
                    entity.getPassword(),
                    entity.getRoles().stream()
                        .map(r -> new Role(r.getName()))
                        .collect(Collectors.toSet())
                );
                user.setId(entity.getId());
                return user;
            });
    }

    @Override
    public Optional<User> findById(Long id) {
        return springDataUserRepository.findById(id)
            .map(entity -> {
                User user = new User(
                    entity.getUsername(),
                    entity.getEmail(),
                    entity.getPassword(),
                    entity.getRoles().stream()
                        .map(r -> new Role(r.getName()))
                        .collect(Collectors.toSet())
                );
                user.setId(entity.getId());
                return user;
            });
    }

    // Interface interna de Spring Data JPA
    interface SpringDataUserRepository extends org.springframework.data.jpa.repository.JpaRepository<UserEntity, Long> {
        Optional<UserEntity> findByEmail(String email);
    }
}