package com.numiAI.api.infrastructure.persistence.repository;

import com.numiAI.api.domain.model.TransactionCategory;
import com.numiAI.api.infrastructure.persistence.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TransactionJpaRepository extends JpaRepository<TransactionEntity, UUID> {
    List<TransactionEntity> findByUserId(UUID userId);
    Optional<TransactionEntity> findByIdAndUserId(UUID id, UUID userId);
    List<TransactionEntity> findByUserIdAndDateBetween(UUID userId, LocalDate from, LocalDate to);
    List<TransactionEntity> findByUserIdAndCategory(UUID userId, TransactionCategory category);
    void deleteByIdAndUserId(UUID id, UUID userId);
}
