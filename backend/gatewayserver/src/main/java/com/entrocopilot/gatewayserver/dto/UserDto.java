package com.entrocopilot.gatewayserver.dto;

import java.time.LocalDateTime;
import java.util.Set;

public class UserDto {
    private String userId;
    private String username;
    private String email; // Ensure this is unique

    private Set<Role> roles; // Enum: Admin, HR, Manager, Employee
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

    // Getters and Setters
    
}
