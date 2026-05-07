package com.numiAI.api.infrastructure.persistence.mapper;

import com.numiAI.api.domain.model.Transaction;
import com.numiAI.api.infrastructure.persistence.entity.TransactionEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

public class TransactionMapper {

    private TransactionMapper() {}

    public static Transaction toDomain(TransactionEntity entity) {
        return new Transaction(
                entity.getId(),
                entity.getUserId(),
                entity.getDescription(),
                entity.getAmount(),
                entity.getType(),
                entity.getCategory(),
                entity.getDate()
        );
    }

    public static TransactionEntity toEntity(Transaction domain) {
        TransactionEntity entity = new TransactionEntity();
        entity.setId(domain.getId());
        entity.setUserId(domain.getUserId());
        entity.setDescription(domain.getDescription());
        entity.setAmount(domain.getAmount());
        entity.setType(domain.getType());
        entity.setCategory(domain.getCategory());
        entity.setDate(domain.getDate());
        return entity;
    }
}
