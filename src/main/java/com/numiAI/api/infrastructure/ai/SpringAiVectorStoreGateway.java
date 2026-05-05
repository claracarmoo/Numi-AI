package com.numiAI.api.infrastructure.ai;

import com.numiAI.api.application.port.out.VectorStoreGateway;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class SpringAiVectorStoreGateway implements VectorStoreGateway {

    // TODO: injetar VectorStore do Spring AI aqui
    // private final VectorStore vectorStore;

    @Override
    public void indexUserData(UUID userId, String content) {
        // TODO: criar Document com metadata userId e salvar no VectorStore
    }

    @Override
    public List<String> search(UUID userId, String query, int topK) {
        // TODO: buscar documentos similares filtrando por userId (guardrail multi-tenant)
        return List.of("Contexto financeiro simulado para: " + query);
    }
}
