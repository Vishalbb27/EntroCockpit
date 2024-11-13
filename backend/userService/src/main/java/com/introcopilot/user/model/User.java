package com.introcopilot.user.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import org.springframework.data.mongodb.core.mapping.DBRef;

import java.time.LocalDateTime;
import java.util.Set;



@Document(collection = "users")
public class User {
    @Id
    private String userId;
    
    private String username;
    private String email; // Ensure this is unique
    private String passwordHash;

    @DBRef
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
	public String getPasswordHash() {
		return passwordHash;
	}
	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
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
	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", email=" + email + ", passwordHash="
				+ passwordHash + ", roles=" + roles + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}
	public User(String userId, String username, String email, String passwordHash, Set<Role> roles,
			LocalDateTime createdAt, LocalDateTime updatedAt) {
		super();
		this.userId = userId;
		this.username = username;
		this.email = email;
		this.passwordHash = passwordHash;
		this.roles = roles;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
	
	public User() {
		
	}
    // Getters and Setters
}

