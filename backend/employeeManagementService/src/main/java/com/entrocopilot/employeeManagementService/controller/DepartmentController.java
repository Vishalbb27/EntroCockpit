package com.entrocopilot.employeeManagementService.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entrocopilot.employeeManagementService.dto.DepartmentDto;
import com.entrocopilot.employeeManagementService.service.DepartmentService;

@RestController
@RequestMapping("/api/employee/department")
public class DepartmentController {
	private DepartmentService departmentService;

	public DepartmentController(DepartmentService departmentService) {
		super();
		this.departmentService = departmentService;
	}

	@PostMapping
	public ResponseEntity<DepartmentDto> createDepartment(@RequestBody DepartmentDto departmentDto) {
		DepartmentDto deDto = departmentService.createDepartment(departmentDto);
		return new ResponseEntity<DepartmentDto>(deDto, HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<DepartmentDto> updateDepartment(@RequestBody DepartmentDto departmentDto) {
		DepartmentDto deDto = departmentService.createDepartment(departmentDto);
		return new ResponseEntity<DepartmentDto>(deDto, HttpStatus.ACCEPTED);
	}
	
	@GetMapping
	public ResponseEntity<List<DepartmentDto>> getAllDepartments(){
		List<DepartmentDto> departmentDtos = departmentService.getAllDepartments();
		return new ResponseEntity<List<DepartmentDto>>(departmentDtos,HttpStatus.OK);
	}
}
