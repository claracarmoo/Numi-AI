package com.numiAI.api.application.usecase;

import com.numiAI.api.application.dto.CreateTransactionCommand;
import com.numiAI.api.application.port.in.ManageTransactionUseCase;
import com.numiAI.api.domain.exception.ResourceNotFoundException;
import com.numiAI.api.domain.model.Transaction;
import com.numiAI.api.domain.repository.TransactionRepository;

import java.util.List;
import java.util.UUID;

public class ManageTransactionUseCaseImpl implements ManageTransactionUseCase {

    private final TransactionRepository transactionRepository;

    public ManageTransactionUseCaseImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Transaction create(CreateTransactionCommand command) {
        Transaction transaction = new Transaction(
                UUID.randomUUID(),
                command.userId(),
                command.description(),
                command.amount(),
                command.type(),
                command.category(),
                command.date()
        );
        return transactionRepository.save(transaction);
    }

    @Override
    public List<Transaction> listByUser(UUID userId) {
        return transactionRepository.findByUserId(userId);
    }

    @Override
    public void delete(UUID transactionId, UUID userId) {
        transactionRepository.findByIdAndUserId(transactionId, userId)
                .orElseThrow(() -> new ResourceNotFoundException("Transação", transactionId));
        transactionRepository.deleteByIdAndUserId(transactionId, userId);
    }
}
