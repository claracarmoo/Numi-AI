package com.numiAI.api.presentation.dto;

import com.numiAI.api.domain.model.Transaction;
import com.numiAI.api.domain.model.TransactionCategory;
import com.numiAI.api.domain.model.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record TransactionResponse(
        UUID id,
        String description,
        BigDecimal amount,
        TransactionType type,
        TransactionCategory category,
        LocalDate date
) {
    public static TransactionResponse from(Transaction t) {
        return new TransactionResponse(t.getId(), t.getDescription(), t.getAmount(), t.getType(), t.getCategory(), t.getDate());
    }
}
