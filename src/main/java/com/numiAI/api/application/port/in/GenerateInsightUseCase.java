package com.numiAI.api.application.port.in;

import java.util.UUID;

public interface GenerateInsightUseCase {
    String generateMonthlyReport(UUID userId, int year, int month);
    String answerQuestion(UUID userId, String question);
}
