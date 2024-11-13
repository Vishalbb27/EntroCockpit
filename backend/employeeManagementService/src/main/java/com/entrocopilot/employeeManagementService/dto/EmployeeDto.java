package com.entrocopilot.employeeManagementService.dto;

import java.time.LocalDateTime;

import org.springframework.data.mongodb.core.mapping.DBRef;

public class EmployeeDto {
	private String employeeId;
	private String userId;
	private String department;
	private String reportingManagerId;
	private String status;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
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
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
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
	public EmployeeDto() {
		super();
	}
	public EmployeeDto(String employeeId, String userId, String department, String reportingManagerId, String status,
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
	@Override
	public String toString() {
		return "EmployeeDto [employeeId=" + employeeId + ", userId=" + userId + ", department=" + department
				+ ", reportingManagerId=" + reportingManagerId + ", status=" + status + ", createdAt=" + createdAt
				+ ", updatedAt=" + updatedAt + "]";
	}
	
	
}
