package com.introcopilot.user.service;

import com.introcopilot.user.dto.JwtAuthResponse;
import com.introcopilot.user.dto.LoginDto;
import com.introcopilot.user.dto.RegisterDto;
import com.introcopilot.user.dto.UserDto;
import com.introcopilot.user.model.User;

import java.util.List;

public interface UserService {
	public UserDto register(RegisterDto registerDto);
	
	public JwtAuthResponse login(LoginDto loginDto);
	
	public UserDto updatePassword(String password, String id);
	
	public List<UserDto> getAllUsers();
	
	public Boolean getUserDetails(String id);
	
//	public Boolean validateToken(String token);
}
