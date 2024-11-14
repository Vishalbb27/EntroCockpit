package com.entrocopilot.projectManagementService.mapper;

import com.entrocopilot.projectManagementService.dto.ProjectDto;
import com.entrocopilot.projectManagementService.model.Project;

public class ProjectMapper {
	public static ProjectDto mapToProjectDto(Project project, ProjectDto projectDto) {
		projectDto.setProjectId(project.getProjectId());
		projectDto.setProjectName(project.getProjectName());
		projectDto.setDescription(project.getDescription());
		projectDto.setStatus(project.getStatus());
		projectDto.setStartDate(project.getStartDate());
		projectDto.setEndDate(project.getEndDate());
		projectDto.setCreatedAt(project.getCreatedAt());
		projectDto.setUpdatedAt(project.getUpdatedAt());
		return projectDto;
	}
	public static Project mapToProject(Project project, ProjectDto projectDto) {
		project.setProjectId(projectDto.getProjectId());
		project.setProjectName(projectDto.getProjectName());
		project.setDescription(projectDto.getDescription());
		project.setStatus(projectDto.getStatus());
		project.setStartDate(projectDto.getStartDate());
		project.setEndDate(projectDto.getEndDate());
		projectDto.setCreatedAt(project.getCreatedAt());
		projectDto.setUpdatedAt(project.getUpdatedAt());
		return project;
	}
}
