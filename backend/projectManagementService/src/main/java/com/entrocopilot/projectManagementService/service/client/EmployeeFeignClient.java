package com.entrocopilot.projectManagementService.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="user",url = "http://localhost:8072",configuration = FeignConfig.class)
public interface EmployeeFeignClient {
	
	@GetMapping(value="/api/employee/validateEmployee",consumes="application/json")
	public ResponseEntity<Boolean> getEmployeeDetails(@RequestParam("id") String id);

}
