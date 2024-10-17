package com.projects.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projects.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserService userService;

	// Endpoint to get a user's status (online or offline)
	@GetMapping("/status/{userId}")
	public Optional<String> getUserStatus(@PathVariable String userId) {
		return userService.getUserStatus(userId);
	}

	// Endpoint to mark a user as online
	@PostMapping("/online/{userId}")
	public String setUserOnline(@PathVariable String userId) {
		userService.setUserOnline(userId);
		return "User " + userId + " is now online.";
	}

	// Endpoint to mark a user as offline
	@PostMapping("/offline/{userId}")
	public String setUserOffline(@PathVariable String userId) {
		userService.setUserOffline(userId);
		return "User " + userId + " is now offline.";
	}

	// Endpoint to remove a user from tracking (e.g., if they log out or leave the
	// system)
	@DeleteMapping("/remove/{userId}")
	public String removeUserStatus(@PathVariable String userId) {
		userService.removeUserStatus(userId);
		return "User " + userId + " status removed.";
	}

}
