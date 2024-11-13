package com.introcopilot.user.security;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.introcopilot.user.model.User;
import com.introcopilot.user.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	private UserRepository userRepository;
	
	public CustomUserDetailsService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail).get();
		
		Set<GrantedAuthority> authorities = user.getRoles().stream()
				.map((role)-> new SimpleGrantedAuthority(role.getName()))
				.collect(Collectors.toSet());
		return new org.springframework.security.core.userdetails.User(usernameOrEmail, user.getPasswordHash(),authorities);
	}

}
