package com.numiAI.api.presentation.dto;

import com.numiAI.api.domain.model.TransactionCategory;
import com.numiAI.api.domain.model.TransactionType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TransactionRequest(
        @NotBlank String description,
        @NotNull @Positive BigDecimal amount,
        @NotNull TransactionType type,
        @NotNull TransactionCategory category,
        @NotNull LocalDate date
) {}
