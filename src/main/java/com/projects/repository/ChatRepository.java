package com.projects.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projects.entity.ChatMessage;

@Repository
public interface ChatRepository extends JpaRepository<ChatMessage, Integer>{
	
	

}
