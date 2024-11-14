package com.entrocopilot.projectManagementService.service;

import java.util.List;

import com.entrocopilot.projectManagementService.dto.ProjectDto;

public interface ProjectService {
	public ProjectDto createProject(ProjectDto projectDto);
	
	public ProjectDto updateProject(ProjectDto projectDto);
	
	public ProjectDto getProjectDetails(String id);
	
	public List<ProjectDto> getAllProjects();
}
