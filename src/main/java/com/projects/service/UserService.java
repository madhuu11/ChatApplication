package com.projects.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class UserService {

	// Map to store the status of users (online or offline)
	private final Map<String, String> userStatuses = new HashMap<>();

	// Optional to check if a user is online
	public Optional<String> getUserStatus(String userId) {
		// Find user status and return it as an Optional
		return Optional.ofNullable(findUserStatus(userId));
	}

	// Update the status of a user
	public void updateUserStatus(String userId, String status) {
		// Update or set the status of the user (either "Online" or "Offline")
		userStatuses.put(userId, status);
	}

	// Private method to retrieve user status
	private String findUserStatus(String userId) {
		// Look up the user in the map and return their status
		return userStatuses.get(userId); // Returns null if the user is not found
	}

	// Additional method to mark a user as online
	public void setUserOnline(String userId) {
		updateUserStatus(userId, "Online");
	}

	// Additional method to mark a user as offline
	public void setUserOffline(String userId) {
		updateUserStatus(userId, "Offline");
	}

	// Method to remove user status when they are no longer tracked (e.g., logging
	// out permanently)
	public void removeUserStatus(String userId) {
		userStatuses.remove(userId);
	}

}
