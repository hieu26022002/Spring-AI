package com.hieunn.spring_ai_demo.service;

import com.hieunn.spring_ai_demo.mode.ChatRequest;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.content.Media;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ChatService {

    private final ChatClient chatClient;

    public ChatService(ChatClient.Builder builder) {
        chatClient = builder.build();
    }

    public String chatImage(MultipartFile file, String message) {
        Media media = Media.builder()
                .mimeType(MimeTypeUtils.parseMimeType(file.getContentType()))
                .data(file.getResource())
                .build();

        ChatOptions chatOptions = ChatOptions.builder()
                .temperature(0D)
                .build();

        return chatClient.prompt()
                .options(chatOptions)
                .system("You are HieuNN.AI")
                .user(promptUserSpec -> promptUserSpec.media(media)
                        .text(message))
                .call()
                .content();
    }

    public String chat(ChatRequest chatRequest) {
        SystemMessage systemMessage = new SystemMessage("""
                You are HieuNN.AI
                """);
        UserMessage userMessage = new UserMessage(chatRequest.message());

        // Prompt điều chỉnh AI phục vụ theo mục đích của mình
        Prompt prompt = new Prompt(systemMessage, userMessage);

        return chatClient
                .prompt(prompt)
                .call()
                .content();
    }
}
