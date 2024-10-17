package com.projects.controller;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projects.entity.ChatMessage;
import com.projects.service.ChatService;

@RestController
@RequestMapping("/api/chat")
public class ChatController {
	
	@Autowired
	private ChatService chatService;
	
	// Endpoint for sending a private message
    @PostMapping("/send")
    public CompletableFuture<ChatMessage> sendMessage(@RequestParam String content,@RequestParam String sender,@RequestParam(required = false) String receiver) {
        // If receiver is null, it will be treated as a public message
        return chatService.sendMessage(content, sender, receiver);
    }
    
 // Endpoint for broadcasting a message to multiple users
    @PostMapping("/broadcast")
    public CompletableFuture<Void> broadcastMessage(@RequestParam String content,@RequestParam String sender,@RequestParam List<String> users) {
        return chatService.broadcastMessage(content, sender, users);
    }
    
 // Endpoint to get the entire chat history
    @GetMapping("/history")
    public List<ChatMessage> getChatHistory() {
        return chatService.getChatHistory();
    }
    
    @MessageMapping("/send")
    @SendTo("/topic/messages")  // Broadcast to all
    public ChatMessage sendMessage(String message) throws Exception {
        return chatService.processMessage(message);
    }
    
    @MessageMapping("/private-message")
    @SendToUser("/queue/messages")  // Send to a specific user
    public ChatMessage sendPrivateMessage(String message, String receiver) throws Exception {
        return chatService.processPrivateMessage(message, "SenderName", receiver).get();
    }
    
    

}
