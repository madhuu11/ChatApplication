package com.projects.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projects.entity.ChatMessage;
import com.projects.repository.ChatRepository;

@Service
public class ChatService {

	@Autowired
	private ChatRepository repo;

	// Process a message asynchronously with receiver
	public CompletableFuture<ChatMessage> processPrivateMessage(int id, String message, String sender, String receiver,
			LocalDateTime timestamp) {
		return CompletableFuture.supplyAsync(() -> {
			ChatMessage chatMessage = new ChatMessage(id, message, sender, receiver, timestamp);
			repo.save(chatMessage);
			return chatMessage;
		});
	}
	
	// Asynchronous method to send messages
    public CompletableFuture<ChatMessage> sendMessage(String content, String sender, String receiver) {
        return CompletableFuture.supplyAsync(() -> {
            // Simulate asynchronous message processing
            return processMessage(content, sender, receiver);
        });
    }
    
 // Method to process the chat message (can handle public/private chats)
    private ChatMessage processMessage(String content, String sender, String receiver) {
        // Generate timestamp for the message
        LocalDateTime timestamp = LocalDateTime.now();

        // Check if it's a public or private chat
        if (receiver == null) {
            // It's a public chat
            return new ChatMessage(content, sender, "Public", timestamp);
        } else {
            // It's a private message
            return new ChatMessage(content, sender, receiver, timestamp);
        }
    }
    
 // Process a message asynchronously with receiver
    public CompletableFuture<ChatMessage> processPrivateMessage(String message, String sender, String receiver) {
        return CompletableFuture.supplyAsync(() -> new ChatMessage(message, sender, receiver));
    }

    // Synchronous message processing for broadcast (public chat)
    public ChatMessage processMessage(String message) {
        return new ChatMessage(message, "System", null);  // No receiver for public chat
    }

	// Synchronous message processing for broadcast (public chat)
//	public ChatMessage processMessage(String message) {
//		return new ChatMessage(message, "System", null); // No receiver for public chat
//	}
    
 // Asynchronous method to broadcast a message to multiple users
    public CompletableFuture<Void> broadcastMessage(String content, String sender, List<String> users) {
        return CompletableFuture.runAsync(() -> {
            users.forEach(user -> {
                // Asynchronously process and send the message to each user
                ChatMessage message = processMessage(content, sender, user);
                sendMessageToUser(message);
            });
        });
    }
    
 // Simulate sending message to a user
    private void sendMessageToUser(ChatMessage message) {
        // Placeholder logic for sending a message to a user
        System.out.println("Message sent to " + message.getReceiver() + ": " + message.getContent());
    }
	
	// Fetch chat history	
    public List<ChatMessage> getChatHistory() {
        return repo.findAll();  // Retrieve all messages from the database
    }

}
