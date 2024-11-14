package com.entrocopilot.projectManagementService.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "project_members")
public class ProjectMember {
	@Id
	private String projectMemberId;
	
	@DBRef
	private Project project;
	private String employeeId;
	private String role;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	public ProjectMember(String projectMemberId, Project project, String employeeId, String role,
			LocalDateTime createdAt, LocalDateTime updatedAt) {
		super();
		this.projectMemberId = projectMemberId;
		this.project = project;
		this.employeeId = employeeId;
		this.role = role;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
	public ProjectMember() {
		super();
	}
	public String getProjectMemberId() {
		return projectMemberId;
	}
	public void setProjectMemberId(String projectMemberId) {
		this.projectMemberId = projectMemberId;
	}
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
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
