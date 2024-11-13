package com.introcopilot.user.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.introcopilot.user.dto.JwtAuthResponse;
import com.introcopilot.user.dto.LoginDto;
import com.introcopilot.user.dto.RegisterDto;
import com.introcopilot.user.dto.UserDto;
import com.introcopilot.user.model.User;
import com.introcopilot.user.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
	private UserService userService;
	
	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}

	@PostMapping("/register")
	public ResponseEntity<UserDto> registerUser(@RequestBody RegisterDto registerDto){
		UserDto userDto = userService.register(registerDto);
		return new ResponseEntity<UserDto>(userDto,HttpStatus.CREATED);
	}
	
	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> loginUser(@RequestBody LoginDto loginDto){
		JwtAuthResponse userDto = userService.login(loginDto);
		return new ResponseEntity<JwtAuthResponse>(userDto,HttpStatus.OK);
	}
	
	@PutMapping("/updatePassword")
	public ResponseEntity<UserDto> updatePassword(@RequestParam("id") String id, @RequestParam String password){
		UserDto userDto = userService.updatePassword(password,id);
		return new ResponseEntity<UserDto>(userDto,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/users")
	public ResponseEntity<?> getAllUsers(){
		List<UserDto> userDtos = userService.getAllUsers();
		return new ResponseEntity<>(userDtos,HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<Boolean> getUserDetails(@RequestParam("id") String id){
		Boolean userDto = userService.getUserDetails(id);
		return new ResponseEntity<Boolean>(userDto,HttpStatus.OK);
	}
	
}
