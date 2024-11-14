package com.entrocopilot.projectManagementService.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.entrocopilot.projectManagementService.dto.ProjectMemberDto;
import com.entrocopilot.projectManagementService.exception.NotFoundException;
import com.entrocopilot.projectManagementService.mapper.ProjectMemberMapper;
import com.entrocopilot.projectManagementService.model.Project;
import com.entrocopilot.projectManagementService.model.ProjectMember;
import com.entrocopilot.projectManagementService.repository.ProjectMemberRepository;
import com.entrocopilot.projectManagementService.repository.ProjectRepository;
import com.entrocopilot.projectManagementService.service.ProjectMemberService;
import com.entrocopilot.projectManagementService.service.client.EmployeeFeignClient;


@Service
public class ProjectMemberServiceImpl implements ProjectMemberService {

	private ProjectRepository projectRepository;
	private ProjectMemberRepository projectMemberRepository;
	private EmployeeFeignClient employeeFeignClient;

	public ProjectMemberServiceImpl(ProjectRepository projectRepository,
			ProjectMemberRepository projectMemberRepository, EmployeeFeignClient employeeFeignClient) {
		super();
		this.projectRepository = projectRepository;
		this.projectMemberRepository = projectMemberRepository;
		this.employeeFeignClient = employeeFeignClient;
	}

	@Override
	public ProjectMemberDto createProjectMember(ProjectMemberDto projectMemberDto) {
		if(projectMemberRepository.existsByEmployeeId(projectMemberDto.getEmployeeId())) {
			throw new NotFoundException("Employee already in a project");
		}
		Boolean employeeExists = employeeFeignClient.getEmployeeDetails(projectMemberDto.getEmployeeId()).getBody();
		if (employeeExists) {
			throw new NotFoundException("Employee Not Found");
		}

		ProjectMember projectMember = new ProjectMember();
		Project project = projectRepository.findById(projectMemberDto.getProject()).orElseThrow(() ->new NotFoundException("Project Not Found"));
		projectMember = ProjectMemberMapper.mapToProjectMember(projectMember, projectMemberDto);
		projectMember.setProject(project);
		projectMember.setCreatedAt(LocalDateTime.now());
		projectMember.setUpdatedAt(LocalDateTime.now());
		projectMember = projectMemberRepository.save(projectMember);
		projectMemberDto = ProjectMemberMapper.mapToProjectMemberDto(projectMember, projectMemberDto);
		return projectMemberDto;
	}

	@Override
	public ProjectMemberDto updateProjectMember(ProjectMemberDto projectMemberDto) {
		ProjectMember projectMember = projectMemberRepository.findById(projectMemberDto.getProjectMemberId()).orElseThrow(() ->new NotFoundException("Project Member Not Found"));
		if (!projectMemberRepository.existsById(projectMemberDto.getProjectMemberId())) {
		    throw new NotFoundException("Project Not Found");
		}
		Boolean employeeExists = employeeFeignClient.getEmployeeDetails(projectMemberDto.getEmployeeId()).getBody();
		if (employeeExists) {
			throw new NotFoundException("Employee Not Found");
		}
		
		Project project = projectRepository.findById(projectMemberDto.getProject()).orElseThrow(() ->new NotFoundException("Project Not Found"));
		projectMemberDto.setCreatedAt(project.getCreatedAt());
		projectMember = ProjectMemberMapper.mapToProjectMember(projectMember, projectMemberDto);
		projectMember.setProject(project);
		projectMember.setUpdatedAt(LocalDateTime.now());
		projectMember = projectMemberRepository.save(projectMember);
		projectMemberDto = ProjectMemberMapper.mapToProjectMemberDto(projectMember, projectMemberDto);
		return projectMemberDto;
	}

	@Override
	public List<ProjectMemberDto> getProjectMembersByProjectId(String id) {
		Project project = projectRepository.findById(id).orElseThrow(() -> new NotFoundException("Project Not Found"));
		List<ProjectMember> projectMembers = projectMemberRepository.findAllByProject(project);
		List<ProjectMemberDto> projectMemberDtos = projectMembers.stream()
				.map(projectMember -> ProjectMemberMapper.mapToProjectMemberDto(projectMember, new ProjectMemberDto()))
				.collect(Collectors.toList());
		return projectMemberDtos;
	}

	@Override
	public void deleteProjectMember(String id) {
		if (!projectMemberRepository.existsById(id)) {
		    throw new NotFoundException("Project Not Found");
		}

		projectMemberRepository.deleteById(id);
	}

}
