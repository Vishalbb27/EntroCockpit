package com.introcopilot.user.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.introcopilot.user.dto.RegisterDto;
import com.introcopilot.user.dto.UserDto;
import com.introcopilot.user.dto.UserProfileDto;
import com.introcopilot.user.service.UserProfileService;

@RestController
@RequestMapping("/api/user/userProfile")
public class UserProfileController {
	
	private UserProfileService userProfileService;
	
	public UserProfileController(UserProfileService userProfileService) {
		super();
		this.userProfileService = userProfileService;
	}

	@PostMapping
	public ResponseEntity<UserProfileDto> createUserProfile(@RequestBody UserProfileDto userProfileDto){
		UserProfileDto userDto = userProfileService.createUserProfile(userProfileDto);
		return new ResponseEntity<UserProfileDto>(userDto,HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<UserProfileDto> updateUserProfile(@RequestBody UserProfileDto userProfileDto){
		UserProfileDto userDto = userProfileService.updateUserProfile(userProfileDto);
		return new ResponseEntity<UserProfileDto>(userDto,HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<UserProfileDto> registerUser(@RequestParam("id") String id){
		UserProfileDto userDto = userProfileService.getProfileByUserId(id);
		return new ResponseEntity<UserProfileDto>(userDto,HttpStatus.CREATED);
	}
	
	@GetMapping("/validateToken")
	public ResponseEntity<Boolean> validateToken(){
		System.out.println("Inside Validate Token");
		return new ResponseEntity<Boolean>(true,HttpStatus.OK);
	}
}
