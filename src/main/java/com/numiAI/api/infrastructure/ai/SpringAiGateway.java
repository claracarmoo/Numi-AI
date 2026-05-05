package com.numiAI.api.infrastructure.ai;

import com.numiAI.api.application.port.out.AiGateway;
import org.springframework.stereotype.Component;

@Component
public class SpringAiGateway implements AiGateway {

    // TODO: injetar ChatClient do Spring AI aqui
    // private final ChatClient chatClient;

    @Override
    public String chat(String systemPrompt, String userMessage) {
        // TODO: implementar chamada real ao LLM via Spring AI
        // return chatClient.prompt()
        //         .system(systemPrompt)
        //         .user(userMessage)
        //         .call()
        //         .content();
        return "Insight gerado pela IA (implementação pendente)";
    }
}
