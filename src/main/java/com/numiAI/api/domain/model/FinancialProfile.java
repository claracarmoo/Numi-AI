package com.numiAI.api.domain.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.UUID;

public class FinancialProfile {

    private final UUID userId;
    private BigDecimal monthlyIncomeGoal;
    private BigDecimal monthlySavingsGoal;

    public FinancialProfile(UUID userId, BigDecimal monthlyIncomeGoal, BigDecimal monthlySavingsGoal) {
        this.userId = userId;
        this.monthlyIncomeGoal = monthlyIncomeGoal;
        this.monthlySavingsGoal = monthlySavingsGoal;
    }

    public BigDecimal calculateBalance(List<Transaction> transactions) {
        BigDecimal income  = transactions.stream().filter(Transaction::isIncome)
                .map(Transaction::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal expense = transactions.stream().filter(Transaction::isExpense)
                .map(Transaction::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
        return income.subtract(expense);
    }

    public BigDecimal savingsRate(List<Transaction> transactions) {
        BigDecimal income = transactions.stream().filter(Transaction::isIncome)
                .map(Transaction::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
        if (income.compareTo(BigDecimal.ZERO) == 0) return BigDecimal.ZERO;
        BigDecimal balance = calculateBalance(transactions);
        return balance.divide(income, 4, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100));
    }

    public boolean isGoalAchieved(List<Transaction> transactions) {
        return calculateBalance(transactions).compareTo(monthlySavingsGoal) >= 0;
    }

    public UUID getUserId()               { return userId; }
    public BigDecimal getMonthlyIncomeGoal()  { return monthlyIncomeGoal; }
    public BigDecimal getMonthlySavingsGoal() { return monthlySavingsGoal; }
}
