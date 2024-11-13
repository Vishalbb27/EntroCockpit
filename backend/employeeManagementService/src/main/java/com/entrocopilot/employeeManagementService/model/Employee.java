package com.entrocopilot.employeeManagementService.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "employees")
public class Employee {
	@Id
	private String employeeId;
	private String userId;
	@DBRef
	private Department department;
	private String reportingManagerId;
	private String status;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	public Employee(String employeeId, String userId, Department department, String reportingManagerId, String status,
			LocalDateTime createdAt, LocalDateTime updatedAt) {
		super();
		this.employeeId = employeeId;
		this.userId = userId;
		this.department = department;
		this.reportingManagerId = reportingManagerId;
		this.status = status;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
	public Employee() {
		super();
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public String getReportingManagerId() {
		return reportingManagerId;
	}
	public void setReportingManagerId(String reportingManagerId) {
		this.reportingManagerId = reportingManagerId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	
}
