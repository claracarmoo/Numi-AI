package com.numiAI.api.infrastructure.persistence.repository;

import com.numiAI.api.domain.model.User;
import com.numiAI.api.domain.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserRepositoryAdapter implements UserRepository {

    private final UserJpaRepository jpa;

    @Override
    public User save(User user) {
        return UserMapper.toDomain(jpa.save(UserMapper.toEntity(user)));
    }

    @Override
    public Optional<User> findById(UUID id) {
        return jpa.findById(id).map(UserMapper::toDomain);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return jpa.findByEmail(email).map(UserMapper::toDomain);
    }

    @Override
    public boolean existsByEmail(String email) {
        return jpa.existsByEmail(email);
    }

}
