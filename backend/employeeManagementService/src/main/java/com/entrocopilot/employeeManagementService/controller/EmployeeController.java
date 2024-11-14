package com.entrocopilot.employeeManagementService.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.entrocopilot.employeeManagementService.dto.EmployeeDto;

import com.entrocopilot.employeeManagementService.service.EmployeeService;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
	
	private EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
		super();
		this.employeeService = employeeService;
	}
	
	@PostMapping
	public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto){
		EmployeeDto empDto = employeeService.createEmployee(employeeDto);
		return new ResponseEntity<EmployeeDto>(empDto,HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<EmployeeDto> updateEmployee(@RequestBody EmployeeDto employeeDto){
		EmployeeDto empDto = employeeService.updateEmployee(employeeDto);
		return new ResponseEntity<EmployeeDto>(empDto,HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<EmployeeDto> getEmployeeById(@RequestParam("id") String id){
		EmployeeDto empDto = employeeService.getEmployeeDetails(id);
		return new ResponseEntity<EmployeeDto>(empDto,HttpStatus.CREATED);
	}
	
	@GetMapping("/validateEmployee")
	public ResponseEntity<Boolean> validateEmployee(@RequestParam("id") String id){
		Boolean employeeExists = employeeService.validateEmployee(id);
		return new ResponseEntity<Boolean>(employeeExists,HttpStatus.CREATED);
	}
	
	@GetMapping("/user")
	public ResponseEntity<EmployeeDto> getEmployeeByUserId(@RequestParam("id") String id){
		EmployeeDto empDto = employeeService.getEmployeeByUserId(id);
		return new ResponseEntity<EmployeeDto>(empDto,HttpStatus.CREATED);
	}
	
	@GetMapping("/reportingManager")
	public ResponseEntity<List<EmployeeDto>> getEmployeeByReportingManagerId(@RequestParam("id") String id){
		List<EmployeeDto> empDto = employeeService.getEmployeesByReportingManagerId(id);
		return new ResponseEntity<List<EmployeeDto>>(empDto,HttpStatus.CREATED);
	}
	
	@GetMapping("/departmentId")
	public ResponseEntity<List<EmployeeDto>> getEmployeeByDepartmentId(@RequestParam("id") String id){
		List<EmployeeDto> empDto = employeeService.getEmployeesByDepartment(id);
		return new ResponseEntity<List<EmployeeDto>>(empDto,HttpStatus.CREATED);
	}
	
	
	@DeleteMapping
	public ResponseEntity<?> deleteEmployeeById(@RequestParam("id") String id){
		employeeService.deleteEmployee(id);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
}
