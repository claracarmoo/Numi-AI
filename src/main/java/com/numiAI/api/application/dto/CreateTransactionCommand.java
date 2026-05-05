package com.numiAI.api.application.dto;

import com.numiAI.api.domain.model.TransactionCategory;
import com.numiAI.api.domain.model.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record CreateTransactionCommand(
        UUID userId,
        String description,
        BigDecimal amount,
        TransactionType type,
        TransactionCategory category,
        LocalDate date
) {}
