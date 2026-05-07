package com.numiAI.api.application.port.out;

import java.util.List;
import java.util.UUID;

public interface VectorStoreGateway {
    void indexUserData(UUID userId, String content);
    List<String> search(UUID userId, String query, int topK);
}
