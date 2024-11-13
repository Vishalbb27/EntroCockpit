package com.introcopilot.user.service.impl;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.introcopilot.user.dto.JwtAuthResponse;
import com.introcopilot.user.dto.LoginDto;
import com.introcopilot.user.dto.RegisterDto;
import com.introcopilot.user.dto.UserDto;
import com.introcopilot.user.exception.APIException;
import com.introcopilot.user.mapper.UserMapper;
import com.introcopilot.user.model.Role;
import com.introcopilot.user.model.User;
import com.introcopilot.user.repository.RoleRepository;
import com.introcopilot.user.repository.UserRepository;
import com.introcopilot.user.security.JwtHelper;
import com.introcopilot.user.service.UserService;
import com.introcopilot.user.token.Token;
import com.introcopilot.user.token.TokenRepository;
import com.introcopilot.user.token.TokenType;


@Service
public class UserServiceImpl implements UserService {
	private UserRepository userRepository;
//	private PasswordEncoder passwordEncoder;
	private RoleRepository roleRepository;
	private BCryptPasswordEncoder byBCryptPasswordEncoder;
	private AuthenticationManager authenticationManager;
	private UserDetailsService userDetailsService;
	private JwtHelper jwtHelper;
	private TokenRepository tokenRepository;
	
	

	public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository,
			BCryptPasswordEncoder byBCryptPasswordEncoder, AuthenticationManager authenticationManager,
			UserDetailsService userDetailsService, JwtHelper jwtHelper, TokenRepository tokenRepository) {
		super();
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.byBCryptPasswordEncoder = byBCryptPasswordEncoder;
		this.authenticationManager = authenticationManager;
		this.userDetailsService = userDetailsService;
		this.jwtHelper = jwtHelper;
		this.tokenRepository = tokenRepository;
	}

	@Override
	public UserDto register(RegisterDto registerDto) {
		if (userRepository.existsByUsername(registerDto.getUsername())) {
			throw new APIException(HttpStatus.BAD_REQUEST, "Username already exists");
		}

		if (userRepository.existsByEmail(registerDto.getEmail())) {
			throw new APIException(HttpStatus.BAD_REQUEST, "Email already exists!");
		}
		
		User user = new User();
		
		user.setEmail(registerDto.getEmail());
		user.setUsername(registerDto.getUsername());
		user.setPasswordHash(byBCryptPasswordEncoder.encode(registerDto.getPassword()));
		
		Set<Role> roles = new HashSet();
		Role userRole = roleRepository.findByName("ROLE_USER");
		roles.add(userRole);
		user.setRoles(roles);
		
		user.setCreatedAt(LocalDateTime.now());
		user.setUpdatedAt(LocalDateTime.now());
		User newUser = userRepository.save(user);
		
		UserDto userDto = new UserDto();
		userDto = UserMapper.mapToUserDto(newUser, userDto);
		return userDto;
	}

	@Override
	public JwtAuthResponse login(LoginDto loginDto) {
//		User user = userRepository.findByUsernameOrEmail(loginDto.getUsernameOrEmail(), loginDto.getUsernameOrEmail()).orElseThrow(()->new APIException(HttpStatus.NOT_FOUND,"User does not exists"));
		
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(), loginDto.getPassword()));
		
		
		if(!authentication.isAuthenticated()) {
			throw new APIException(HttpStatus.NOT_FOUND, "Username does not exist please register.");
		}
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
		
		User userInfo = userRepository.findByUsername(loginDto.getUsernameOrEmail()).get();
		
		UserDetails userDetails= userDetailsService.loadUserByUsername(loginDto.getUsernameOrEmail());
		String jwttoken = jwtHelper.generateToken(userDetails);
		jwtAuthResponse.setAccessToken(jwttoken);
		revokeAllUserTokens(userInfo);
		saveUserToken(userInfo, jwttoken);

		String role = null;
		Optional<User> userOptional =userRepository.findByUsernameOrEmail(loginDto.getUsernameOrEmail(), loginDto.getUsernameOrEmail());
		if(userOptional.isPresent()) {
			User user= userOptional.get();
			Optional<Role> optionalRole = user.getRoles().stream().findFirst();
			if(optionalRole.isPresent()) {
				Role userRole = optionalRole.get();
				role = userRole.getName();
			}
		}

		jwtAuthResponse.setRole(role);
		return jwtAuthResponse;
	}

	@Override
	public UserDto updatePassword(String password, String id) {
		User user = userRepository.findById(id).orElseThrow(()-> new APIException(HttpStatus.NOT_FOUND, "User not found"));
		user.setPasswordHash(password);
		user.setUpdatedAt(LocalDateTime.now());
		userRepository.save(user);
		UserDto userDto = new UserDto();
		userDto = UserMapper.mapToUserDto(user, userDto);
		return userDto;
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> users = userRepository.findAll();
		List<UserDto> userDtos = users.stream().map(user -> UserMapper.mapToUserDto(user, new UserDto())).collect(Collectors.toList());
		return userDtos;
	}

	@Override
	public Boolean getUserDetails(String id) {
		return userRepository.existsById(id);
//		return UserMapper.mapToUserDto(user, new UserDto());
	}
	
	
	private void saveUserToken(User userInfo, String jwttoken) {
		Token token = new Token(jwttoken,TokenType.BEARER,userInfo,false,false);
		tokenRepository.save(token);
	}
	
	private void revokeAllUserTokens(User user) {
		List<Token> validToken = tokenRepository.findAllValidTokenByUser(user.getUserId());
		if(validToken.isEmpty()) {
			return;
		}
		validToken.forEach(t-> {
			t.setExpired(true);
			t.setRevoked(true);
		});
		tokenRepository.saveAll(validToken);
	}

}
