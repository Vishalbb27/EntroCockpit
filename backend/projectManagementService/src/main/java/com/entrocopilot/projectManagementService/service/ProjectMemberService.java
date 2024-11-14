package com.entrocopilot.projectManagementService.service;

import java.util.List;

import com.entrocopilot.projectManagementService.dto.ProjectMemberDto;

public interface ProjectMemberService {
	public ProjectMemberDto createProjectMember(ProjectMemberDto projectMemberDto);
	
	public ProjectMemberDto updateProjectMember(ProjectMemberDto projectMemberDto);
	
	public List<ProjectMemberDto> getProjectMembersByProjectId(String id);
	
	public void deleteProjectMember(String id);
}
