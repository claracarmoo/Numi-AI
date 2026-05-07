package com.numiAI.api.infrastructure;

import com.numiAI.api.application.port.in.GenerateInsightUseCase;
import com.numiAI.api.application.port.in.ManageTransactionUseCase;
import com.numiAI.api.application.port.out.AiGateway;
import com.numiAI.api.application.port.out.VectorStoreGateway;
import com.numiAI.api.application.usecase.GenerateInsightUseCaseImpl;
import com.numiAI.api.application.usecase.ManageTransactionUseCaseImpl;
import com.numiAI.api.domain.repository.TransactionRepository;
import com.numiAI.api.domain.service.FinancialAnalysisService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    public FinancialAnalysisService financialAnalysisService() {
        return new FinancialAnalysisService();
    }

    @Bean
    public ManageTransactionUseCase manageTransactionUseCase(
            TransactionRepository transactionRepository) {
        return (ManageTransactionUseCase) new ManageTransactionUseCaseImpl(transactionRepository);
    }

    @Bean
    public GenerateInsightUseCase generateInsightUseCase(
            TransactionRepository transactionRepository,
            FinancialAnalysisService analysisService,
            AiGateway aiGateway,
            VectorStoreGateway vectorStoreGateway) {
        return (GenerateInsightUseCase) new GenerateInsightUseCaseImpl(
                transactionRepository, analysisService, aiGateway, vectorStoreGateway);
    }
}
