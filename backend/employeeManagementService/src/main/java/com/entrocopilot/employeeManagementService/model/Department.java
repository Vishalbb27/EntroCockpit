package com.entrocopilot.employeeManagementService.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "departments")
public class Department {
	@Id
	private String departmentId;
	private String departmentName;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	public String getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
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
	public Department(String departmentId, String departmentName, LocalDateTime createdAt, LocalDateTime updatedAt) {
		super();
		this.departmentId = departmentId;
		this.departmentName = departmentName;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
	public Department() {
		super();
	}
	@Override
	public String toString() {
		return "Department [departmentId=" + departmentId + ", departmentName=" + departmentName + ", createdAt="
				+ createdAt + ", updatedAt=" + updatedAt + "]";
	}
	

}
