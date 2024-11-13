package com.entrocopilot.employeeManagementService.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.entrocopilot.employeeManagementService.model.Department;


public interface DepartmentRepository extends MongoRepository<Department, String> {
	Boolean existsByDepartmentName(String name);
}
