package com.entrocopilot.projectManagementService.mapper;

import com.entrocopilot.projectManagementService.dto.ProjectMemberDto;
import com.entrocopilot.projectManagementService.model.Project;
import com.entrocopilot.projectManagementService.model.ProjectMember;

public class ProjectMemberMapper {
	public static ProjectMemberDto mapToProjectMemberDto(ProjectMember projectMember,ProjectMemberDto projectMemberDto) {
		projectMemberDto.setProjectMemberId(projectMember.getProjectMemberId());
		projectMemberDto.setProject(projectMember.getProject().getProjectId());
		projectMemberDto.setEmployeeId(projectMember.getEmployeeId());
		projectMemberDto.setRole(projectMember.getRole());
		projectMemberDto.setCreatedAt(projectMember.getCreatedAt());
		projectMemberDto.setUpdatedAt(projectMember.getUpdatedAt());
		return projectMemberDto;
	}
	
	public static ProjectMember mapToProjectMember(ProjectMember projectMember,ProjectMemberDto projectMemberDto) {
		projectMember.setProjectMemberId(projectMemberDto.getProjectMemberId());
		projectMember.setEmployeeId(projectMemberDto.getEmployeeId());
		projectMember.setRole(projectMemberDto.getRole());
		projectMember.setCreatedAt(projectMemberDto.getCreatedAt());
		projectMember.setUpdatedAt(projectMemberDto.getUpdatedAt());
		return projectMember;
	}
}
