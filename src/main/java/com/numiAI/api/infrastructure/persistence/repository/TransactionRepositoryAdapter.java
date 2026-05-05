package com.numiAI.api.infrastructure.persistence.repository;

import com.numiAI.api.domain.model.Transaction;
import com.numiAI.api.domain.model.TransactionCategory;
import com.numiAI.api.domain.repository.TransactionRepository;
import com.numiAI.api.infrastructure.persistence.mapper.TransactionMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class TransactionRepositoryAdapter implements TransactionRepository {

    private final TransactionJpaRepository jpa;

    public TransactionRepositoryAdapter(TransactionJpaRepository jpa) {
        this.jpa = jpa;
    }

    @Override
    public Transaction save(Transaction transaction) {
        return TransactionMapper.toDomain(jpa.save(TransactionMapper.toEntity(transaction)));
    }

    @Override
    public Optional<Transaction> findByIdAndUserId(UUID id, UUID userId) {
        return jpa.findByIdAndUserId(id, userId).map(TransactionMapper::toDomain);
    }

    @Override
    public List<Transaction> findByUserId(UUID userId) {
        return jpa.findByUserId(userId).stream().map(TransactionMapper::toDomain).toList();
    }

    @Override
    public List<Transaction> findByUserIdAndDateBetween(UUID userId, LocalDate from, LocalDate to) {
        return jpa.findByUserIdAndDateBetween(userId, from, to).stream().map(TransactionMapper::toDomain).toList();
    }

    @Override
    public List<Transaction> findByUserIdAndCategory(UUID userId, TransactionCategory category) {
        return jpa.findByUserIdAndCategory(userId, category).stream().map(TransactionMapper::toDomain).toList();
    }

    @Override
    public void deleteByIdAndUserId(UUID id, UUID userId) {
        jpa.deleteByIdAndUserId(id, userId);
    }
}
