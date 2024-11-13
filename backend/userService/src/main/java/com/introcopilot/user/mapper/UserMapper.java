package com.introcopilot.user.mapper;

import com.introcopilot.user.dto.UserDto;
import com.introcopilot.user.model.User;

public class UserMapper {
	public static UserDto mapToUserDto(User user, UserDto userDto) {
		userDto.setUserId(user.getUserId());
		userDto.setEmail(user.getEmail());
		userDto.setCreatedAt(user.getCreatedAt());
		userDto.setUpdatedAt(user.getUpdatedAt());
		userDto.setUsername(user.getUsername());
		userDto.setRoles(user.getRoles());
		return userDto;
	}
	
	public static User mapToUser(User user,UserDto userDto) {
		user.setUserId(userDto.getUserId());
		user.setCreatedAt(userDto.getCreatedAt());
		user.setUpdatedAt(userDto.getUpdatedAt());
		user.setEmail(userDto.getEmail());
		user.setRoles(userDto.getRoles());
		user.setUsername(userDto.getUsername());
		return user;
	}
}
