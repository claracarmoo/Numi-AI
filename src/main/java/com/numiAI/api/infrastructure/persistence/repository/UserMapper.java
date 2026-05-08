package com.numiAI.api.infrastructure.persistence.repository;

import com.numiAI.api.domain.model.User;
import com.numiAI.api.infrastructure.persistence.entity.UserEntity;

public class UserMapper {
    private UserMapper() {}

    public static User toDomain(UserEntity entity) {
        return new User(
                entity.getId(),
                entity.getName(),
                entity.getEmail(),
                entity.getPasswordHash()
        );
    }

    public static UserEntity toEntity(User domain) {
        UserEntity entity = new UserEntity();

        entity.setName(domain.getName());
        entity.setEmail(domain.getEmail());
        entity.setPasswordHash(domain.getPasswordHash());

        return entity;
    }
}