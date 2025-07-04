package com.hieunn.spring_ai_demo.service;

import com.hieunn.spring_ai_demo.mode.ChatRequest;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

    private final ChatClient chatClient;

    public ChatService(ChatClient.Builder builder) {
        chatClient = builder.build();
    }

    public String chat(ChatRequest chatRequest) {
        return chatClient
                .prompt(chatRequest.message())
                .call()
                .content();
    }
}
