package com.numiAI.api.application.port.out;

public interface AiGateway {
    String chat(String systemPrompt, String userMessage);
}
