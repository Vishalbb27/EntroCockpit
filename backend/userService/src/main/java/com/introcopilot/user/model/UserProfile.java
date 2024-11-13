package com.introcopilot.user.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import org.springframework.data.mongodb.core.mapping.DBRef;

import java.time.LocalDateTime;


@Document(collection = "user_profiles")
public class UserProfile {
    @Id
    private String profileId;

    @DBRef
    private User user; // Reference to User

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
	public String getProfileId() {
		return profileId;
	}
	public void setProfileId(String profileId) {
		this.profileId = profileId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
	public UserProfile(String profileId, User user, String firstName, String lastName, String phoneNumber,
			String address, LocalDateTime createdAt, LocalDateTime updatedAt) {
		super();
		this.profileId = profileId;
		this.user = user;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
	public UserProfile() {
		
	}
	@Override
	public String toString() {
		return "UserProfile [profileId=" + profileId + ", user=" + user + ", firstName=" + firstName + ", lastName="
				+ lastName + ", phoneNumber=" + phoneNumber + ", address=" + address + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}
    
}
