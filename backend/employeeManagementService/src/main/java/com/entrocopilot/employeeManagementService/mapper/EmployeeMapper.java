package com.entrocopilot.employeeManagementService.mapper;

import com.entrocopilot.employeeManagementService.dto.EmployeeDto;
import com.entrocopilot.employeeManagementService.model.Employee;

public class EmployeeMapper {
	
	public static EmployeeDto mapToEmployeeDto(Employee employee,EmployeeDto employeeDto) {
		employeeDto.setEmployeeId(employee.getEmployeeId());
		employeeDto.setDepartment(employee.getDepartment().getDepartmentId());
		employeeDto.setUserId(employee.getUserId());
		employeeDto.setReportingManagerId(employee.getReportingManagerId());
		employeeDto.setStatus(employee.getStatus());
		employeeDto.setCreatedAt(employee.getCreatedAt());
		employeeDto.setUpdatedAt(employee.getUpdatedAt());
		return employeeDto;
	}
	
	public static Employee mapToEmployee(Employee employee,EmployeeDto employeeDto) {
		employee.setEmployeeId(employeeDto.getEmployeeId());
		employee.setUserId(employeeDto.getUserId());
		employee.setReportingManagerId(employeeDto.getReportingManagerId());
		employee.setStatus(employeeDto.getStatus());
		employee.setCreatedAt(employeeDto.getCreatedAt());
		employee.setUpdatedAt(employeeDto.getUpdatedAt());
		return employee;
	}
}
