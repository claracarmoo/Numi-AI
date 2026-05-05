package com.numiAI.api.domain.model;

import com.numiAI.api.domain.exception.DomainException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class Transaction {

    private final UUID id;
    private final UUID userId;
    private String description;
    private BigDecimal amount;
    private TransactionType type;
    private TransactionCategory category;
    private LocalDate date;

    public Transaction(UUID id, UUID userId, String description, BigDecimal amount,
                       TransactionType type, TransactionCategory category, LocalDate date) {
        validate(description, amount, date);
        this.id = id;
        this.userId = userId;
        this.description = description;
        this.amount = amount;
        this.type = type;
        this.category = category;
        this.date = date;
    }

    private void validate(String description, BigDecimal amount, LocalDate date) {
        if (description == null || description.isBlank()) throw new DomainException("Descrição é obrigatória");
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) throw new DomainException("Valor deve ser positivo");
        if (date == null) throw new DomainException("Data é obrigatória");
    }

    public boolean isExpense() { return type == TransactionType.EXPENSE; }
    public boolean isIncome()  { return type == TransactionType.INCOME; }

    public UUID getId()          { return id; }
    public UUID getUserId()      { return userId; }
    public String getDescription() { return description; }
    public BigDecimal getAmount()  { return amount; }
    public TransactionType getType() { return type; }
    public TransactionCategory getCategory() { return category; }
    public LocalDate getDate()   { return date; }
}
