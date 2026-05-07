package com.numiAI.api.domain.repository;

import com.numiAI.api.domain.model.Transaction;
import com.numiAI.api.domain.model.TransactionCategory;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TransactionRepository {
    Transaction save(Transaction transaction);
    Optional<Transaction> findByIdAndUserId(UUID id, UUID userId);
    List<Transaction> findByUserId(UUID userId);
    List<Transaction> findByUserIdAndDateBetween(UUID userId, LocalDate from, LocalDate to);
    List<Transaction> findByUserIdAndCategory(UUID userId, TransactionCategory category);
    void deleteByIdAndUserId(UUID id, UUID userId);
}
