package com.numiAI.api.domain.service;

import com.numiAI.api.domain.model.Transaction;
import com.numiAI.api.domain.model.TransactionCategory;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FinancialAnalysisService {

    public Map<TransactionCategory, BigDecimal> groupExpensesByCategory(List<Transaction> transactions) {
        return transactions.stream()
                .filter(Transaction::isExpense)
                .collect(Collectors.groupingBy(
                        Transaction::getCategory,
                        Collectors.reducing(BigDecimal.ZERO, Transaction::getAmount, BigDecimal::add)
                ));
    }

    public TransactionCategory biggestExpenseCategory(List<Transaction> transactions) {
        return groupExpensesByCategory(transactions).entrySet().stream()
                .max(Comparator.comparing(Map.Entry::getValue))
                .map(Map.Entry::getKey)
                .orElse(TransactionCategory.OTHER);
    }

    public List<Transaction> topExpenses(List<Transaction> transactions, int limit) {
        return transactions.stream()
                .filter(Transaction::isExpense)
                .sorted(Comparator.comparing(Transaction::getAmount).reversed())
                .limit(limit)
                .toList();
    }
}
