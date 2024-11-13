package com.entrocopilot.gatewayserver.service.client;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.entrocopilot.gatewayserver.dto.UserDto;

@Component
public class UserFallback implements UserFeignClient {

	@Override
	public ResponseEntity<Boolean> validateToken() {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
