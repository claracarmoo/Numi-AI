package com.numiAI.api.application.usecase;

import com.numiAI.api.application.port.in.GenerateInsightUseCase;
import com.numiAI.api.application.port.out.AiGateway;
import com.numiAI.api.application.port.out.VectorStoreGateway;
import com.numiAI.api.domain.model.Transaction;
import com.numiAI.api.domain.repository.TransactionRepository;
import com.numiAI.api.domain.service.FinancialAnalysisService;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class GenerateInsightUseCaseImpl implements GenerateInsightUseCase {

    private static final String SYSTEM_PROMPT =
            "Você é um assistente financeiro pessoal. Analise os dados do usuário e forneça insights claros, objetivos e acionáveis em português.";

    private final TransactionRepository transactionRepository;
    private final FinancialAnalysisService analysisService;
    private final AiGateway aiGateway;
    private final VectorStoreGateway vectorStoreGateway;

    public GenerateInsightUseCaseImpl(TransactionRepository transactionRepository,
                                      FinancialAnalysisService analysisService,
                                      AiGateway aiGateway,
                                      VectorStoreGateway vectorStoreGateway) {
        this.transactionRepository = transactionRepository;
        this.analysisService = analysisService;
        this.aiGateway = aiGateway;
        this.vectorStoreGateway = vectorStoreGateway;
    }

    @Override
    public String generateMonthlyReport(UUID userId, int year, int month) {
        LocalDate from = LocalDate.of(year, month, 1);
        LocalDate to = from.withDayOfMonth(from.lengthOfMonth());

        List<Transaction> transactions = transactionRepository.findByUserIdAndDateBetween(userId, from, to);
        String summary = buildTransactionSummary(transactions);

        return aiGateway.chat(SYSTEM_PROMPT,
                "Gere um relatório financeiro mensal com base nas seguintes transações:\n" + summary);
    }

    @Override
    public String answerQuestion(UUID userId, String question) {
        // Pipeline RAG: recupera contexto relevante do vector store
        List<String> context = vectorStoreGateway.search(userId, question, 5);
        String contextText = String.join("\n", context);

        return aiGateway.chat(SYSTEM_PROMPT,
                "Contexto financeiro do usuário:\n" + contextText + "\n\nPergunta: " + question);
    }

    private String buildTransactionSummary(List<Transaction> transactions) {
        return transactions.stream()
                .map(t -> "%s | %s | %s | R$ %.2f".formatted(
                        t.getDate(), t.getType(), t.getCategory(), t.getAmount()))
                .collect(Collectors.joining("\n"));
    }
}
