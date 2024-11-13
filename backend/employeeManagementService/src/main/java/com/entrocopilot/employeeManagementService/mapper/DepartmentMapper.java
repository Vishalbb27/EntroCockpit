package com.entrocopilot.employeeManagementService.mapper;

import com.entrocopilot.employeeManagementService.dto.DepartmentDto;
import com.entrocopilot.employeeManagementService.model.Department;

public class DepartmentMapper {
	public static DepartmentDto mapToDepartmentDto(Department department, DepartmentDto departmentDto) {
		departmentDto.setDepartmentId(department.getDepartmentId());
		departmentDto.setDepartmentName(department.getDepartmentName());
		departmentDto.setCreatedAt(department.getCreatedAt());
		departmentDto.setUpdatedAt(department.getUpdatedAt());
		return departmentDto;
	}
	
	public static Department mapToDepartment(Department department, DepartmentDto departmentDto) {
		department.setDepartmentId(departmentDto.getDepartmentId());
		department.setDepartmentName(departmentDto.getDepartmentName());
		department.setCreatedAt(departmentDto.getCreatedAt());
		department.setUpdatedAt(departmentDto.getUpdatedAt());
		return department;
	}
}
