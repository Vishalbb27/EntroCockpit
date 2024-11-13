package com.introcopilot.user.service;

import com.introcopilot.user.dto.UserProfileDto;

public interface UserProfileService {
	public UserProfileDto createUserProfile(UserProfileDto userProfileDto);
	
	public UserProfileDto updateUserProfile(UserProfileDto userProfileDto);
	
	public UserProfileDto getProfileByUserId(String userId);
}
