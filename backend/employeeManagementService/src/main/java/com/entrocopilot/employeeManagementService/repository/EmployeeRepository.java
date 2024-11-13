package com.entrocopilot.employeeManagementService.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.entrocopilot.employeeManagementService.model.Department;
import com.entrocopilot.employeeManagementService.model.Employee;

public interface EmployeeRepository extends MongoRepository<Employee, String> {
	Employee findByUserId(String id);
	
	List<Employee> findByReportingManagerId(String id);
	
	List<Employee> findByDepartment(Department department);
	
	Boolean existsByUserId(String id);
}
