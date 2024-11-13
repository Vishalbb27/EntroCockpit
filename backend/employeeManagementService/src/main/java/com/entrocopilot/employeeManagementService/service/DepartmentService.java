package com.entrocopilot.employeeManagementService.service;

import java.util.List;

import com.entrocopilot.employeeManagementService.dto.DepartmentDto;

public interface DepartmentService {
	public DepartmentDto createDepartment(DepartmentDto departmentDto);
	
	public DepartmentDto updateDepartment(DepartmentDto departmentDto);
	
	public String deleteDepartment(String id);
	
	public List<DepartmentDto> getAllDepartments();
	
}
