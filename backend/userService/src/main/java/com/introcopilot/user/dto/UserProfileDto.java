package com.introcopilot.user.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class UserProfileDto {
	private String profileId;

    private String user; // Reference to User

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
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
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
	public UserProfileDto(String profileId, String user, String firstName, String lastName, String phoneNumber,
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
    
    public UserProfileDto() {
    	
    }
}
