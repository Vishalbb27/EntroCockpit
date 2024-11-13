package com.entrocopilot.employeeManagementService.dto;

import java.time.LocalDateTime;

public class DepartmentDto {
	private String departmentId;
	private String departmentName;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	public DepartmentDto() {
		super();
	}
	

	public DepartmentDto(String departmentId, String departmentName, LocalDateTime createdAt, LocalDateTime updatedAt) {
		super();
		this.departmentId = departmentId;
		this.departmentName = departmentName;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
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
	@Override
	public String toString() {
		return "DepartmentDto [departmentId=" + departmentId + ", departmentName=" + departmentName + ", createdAt="
				+ createdAt + ", updatedAt=" + updatedAt + "]";
	}
	
	
}
