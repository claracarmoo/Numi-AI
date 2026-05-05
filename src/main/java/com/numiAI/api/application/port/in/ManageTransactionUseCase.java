package com.numiAI.api.application.port.in;

import com.numiAI.api.application.dto.CreateTransactionCommand;
import com.numiAI.api.domain.model.Transaction;

import java.util.List;
import java.util.UUID;

public interface ManageTransactionUseCase {
    Transaction create(CreateTransactionCommand command);
    List<Transaction> listByUser(UUID userId);
    void delete(UUID transactionId, UUID userId);
}
