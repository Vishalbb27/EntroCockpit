package com.entrocopilot.employeeManagementService.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="user",url = "http://localhost:8081",configuration = FeignConfig.class)
public interface UserFeignClient {
	
	@GetMapping(value="/api/user",consumes="application/json")
	public ResponseEntity<Boolean> getUserDetails(@RequestParam("id") String id);

}
