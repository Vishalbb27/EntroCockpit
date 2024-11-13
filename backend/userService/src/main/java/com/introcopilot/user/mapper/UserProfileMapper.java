package com.introcopilot.user.mapper;

import com.introcopilot.user.dto.UserProfileDto;
import com.introcopilot.user.model.User;
import com.introcopilot.user.model.UserProfile;

public class UserProfileMapper {
	public static UserProfileDto mapToUserProfileDto(UserProfile userProfile, UserProfileDto userProfileDto) {
		userProfileDto.setProfileId(userProfile.getProfileId());
		userProfileDto.setFirstName(userProfile.getFirstName());
		userProfileDto.setLastName(userProfile.getLastName());
		userProfileDto.setAddress(userProfile.getAddress());
		userProfileDto.setPhoneNumber(userProfile.getPhoneNumber());
		userProfileDto.setUser(userProfile.getUser().getUserId());
		userProfileDto.setCreatedAt(userProfile.getCreatedAt());
		userProfileDto.setUpdatedAt(userProfile.getUpdatedAt());
		return userProfileDto;
	}
	
	public static UserProfile mapToUserProfile(UserProfile userProfile, UserProfileDto userProfileDto,User user) {
		userProfile.setFirstName(userProfileDto.getFirstName());
		userProfile.setLastName(userProfileDto.getLastName());
		userProfile.setAddress(userProfileDto.getAddress());
		userProfile.setPhoneNumber(userProfileDto.getPhoneNumber());
		userProfile.setUser(user);
		return userProfile;
	}
}
