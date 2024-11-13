package com.entrocopilot.employeeManagementService.service;

import java.util.List;

import com.entrocopilot.employeeManagementService.dto.EmployeeDto;


public interface EmployeeService {
	public EmployeeDto getEmployeeDetails(String id);
	
	public EmployeeDto getEmployeeByUserId(String id);
	
	public EmployeeDto createEmployee(EmployeeDto employeeDto);
	
	public EmployeeDto updateEmployee(EmployeeDto employeeDto);
	
	public void deleteEmployee(String id);
	
	public List<EmployeeDto> getEmployeesByReportingManagerId(String id);
	
	public List<EmployeeDto> getEmployeesByDepartment(String id);
	
	
}
