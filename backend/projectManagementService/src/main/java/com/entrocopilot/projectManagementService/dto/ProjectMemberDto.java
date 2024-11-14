package com.entrocopilot.projectManagementService.dto;

import java.time.LocalDateTime;
import com.entrocopilot.projectManagementService.model.Project;

public class ProjectMemberDto {
	private String projectMemberId;
	private String project;
	private String employeeId;
	private String role;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	public ProjectMemberDto(String projectMemberId, String project, String employeeId, String role,
			LocalDateTime createdAt, LocalDateTime updatedAt) {
		super();
		this.projectMemberId = projectMemberId;
		this.project = project;
		this.employeeId = employeeId;
		this.role = role;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
	public ProjectMemberDto() {
		super();
	}
	public String getProjectMemberId() {
		return projectMemberId;
	}
	public void setProjectMemberId(String projectMemberId) {
		this.projectMemberId = projectMemberId;
	}
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
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
