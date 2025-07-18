package com.hieunn.spring_ai_demo.controller;

import com.hieunn.spring_ai_demo.mode.BillItem;
import com.hieunn.spring_ai_demo.mode.ChatRequest;
import com.hieunn.spring_ai_demo.mode.ExpenseInfo;
import com.hieunn.spring_ai_demo.mode.FilmInfo;
import com.hieunn.spring_ai_demo.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping("/chat")
    String chat(@RequestBody ChatRequest request){
        return chatService.chat(request);
    }

    @PostMapping("/chat-image")
    List<BillItem> chatImage(@RequestParam("file") MultipartFile file,
                             @RequestParam("message") String message){
        return chatService.chatImage(file,message);

    }
}
