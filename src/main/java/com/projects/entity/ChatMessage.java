package com.projects.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="chatmessage")
public class ChatMessage {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String content;
    private String sender;
    private String receiver;
    private LocalDateTime timestamp;
    
    public ChatMessage(String content, String sender, String receiver, LocalDateTime timestamp) {
        this.content = content;
        this.sender = sender;
        this.receiver = receiver;
        this.timestamp = LocalDateTime.now();
    }
    
    public ChatMessage(String message) {
    	this.content = message;
    }

	public ChatMessage(String message, String sender, String receiver) {
		this.content = message;
		this.sender = sender;
		this.receiver = receiver;	
	}

	

}
