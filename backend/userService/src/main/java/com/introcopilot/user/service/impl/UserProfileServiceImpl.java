package com.introcopilot.user.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.introcopilot.user.dto.UserProfileDto;
import com.introcopilot.user.exception.APIException;
import com.introcopilot.user.mapper.UserProfileMapper;
import com.introcopilot.user.model.User;
import com.introcopilot.user.model.UserProfile;
import com.introcopilot.user.repository.UserProfileRepository;
import com.introcopilot.user.repository.UserRepository;
import com.introcopilot.user.service.UserProfileService;

@Service
public class UserProfileServiceImpl implements UserProfileService {
	
	private UserProfileRepository userProfileRepository;
	
	private UserRepository userRepository;
	
	public UserProfileServiceImpl(UserProfileRepository userProfileRepository, UserRepository userRepository) {
		super();
		this.userProfileRepository = userProfileRepository;
		this.userRepository = userRepository;
	}

	@Override
	public UserProfileDto createUserProfile(UserProfileDto userProfileDto) {
		User user = userRepository.findById(userProfileDto.getUser()).orElseThrow(()->new APIException(HttpStatus.NOT_FOUND,"User does not exists"));
		
		UserProfile userProfile = new UserProfile();
		userProfile.setFirstName(userProfileDto.getFirstName());
		userProfile.setLastName(userProfileDto.getLastName());
		userProfile.setAddress(userProfileDto.getAddress());
		userProfile.setPhoneNumber(userProfileDto.getPhoneNumber());
		userProfile.setCreatedAt(LocalDateTime.now());
		userProfile.setUpdatedAt(LocalDateTime.now());
		userProfile.setUser(user);
		
		UserProfile newUserProfile = userProfileRepository.save(userProfile);
		UserProfileDto newUserProfileDto = new UserProfileDto();
		newUserProfileDto = UserProfileMapper.mapToUserProfileDto(newUserProfile, newUserProfileDto);
		
		return newUserProfileDto;
	}

	@Override
	public UserProfileDto updateUserProfile(UserProfileDto userProfileDto) {
		UserProfile userProfile = userProfileRepository.findById(userProfileDto.getProfileId()).orElseThrow(()->new APIException(HttpStatus.NOT_FOUND,"User Profile does not exists"));
		
		userProfile.setFirstName(userProfileDto.getFirstName());
		userProfile.setLastName(userProfileDto.getLastName());
		userProfile.setAddress(userProfileDto.getAddress());
		userProfile.setPhoneNumber(userProfileDto.getPhoneNumber());
		userProfile.setUpdatedAt(LocalDateTime.now());
		
		UserProfile newUserProfile = userProfileRepository.save(userProfile);
		UserProfileDto newUserProfileDto = new UserProfileDto();
		newUserProfileDto = UserProfileMapper.mapToUserProfileDto(newUserProfile, newUserProfileDto);
		
		return newUserProfileDto;
	}

	@Override
	public UserProfileDto getProfileByUserId(String userId) {
		User user = userRepository.findById(userId).orElseThrow(()->new APIException(HttpStatus.NOT_FOUND,"User does not exists"));
		UserProfile userProfile = userProfileRepository.findByUser(user);
		
		if(userProfile.getProfileId()!=null) {
			
			UserProfileDto newUserProfileDto = new UserProfileDto();
			newUserProfileDto = UserProfileMapper.mapToUserProfileDto(userProfile, newUserProfileDto);
			return newUserProfileDto;
		}
		throw new APIException(HttpStatus.BAD_REQUEST, "UserProfile does not exists");
		
	}

}
