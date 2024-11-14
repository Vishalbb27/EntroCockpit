package com.entrocopilot.projectManagementService.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.entrocopilot.projectManagementService.dto.ProjectDto;
import com.entrocopilot.projectManagementService.exception.AlreadyExists;
import com.entrocopilot.projectManagementService.exception.NotFoundException;
import com.entrocopilot.projectManagementService.mapper.ProjectMapper;
import com.entrocopilot.projectManagementService.model.Project;
import com.entrocopilot.projectManagementService.repository.ProjectRepository;
import com.entrocopilot.projectManagementService.service.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService {

	private ProjectRepository projectRepository;

	public ProjectServiceImpl(ProjectRepository projectRepository) {
		super();
		this.projectRepository = projectRepository;
	}

	@Override
	public ProjectDto createProject(ProjectDto projectDto) {
		if (projectRepository.existsByprojectName(projectDto.getProjectName())) {
			throw new AlreadyExists("Project Already Exists");
		}
		Project project = new Project();
		project = ProjectMapper.mapToProject(project, projectDto);
		project.setCreatedAt(LocalDateTime.now());
		project.setUpdatedAt(LocalDateTime.now());
		project = projectRepository.save(project);
		projectDto = ProjectMapper.mapToProjectDto(project, new ProjectDto());
		return projectDto;
	}

	@Override
	public ProjectDto updateProject(ProjectDto projectDto) {
		Project project = projectRepository.findById(projectDto.getProjectId()).orElseThrow(() -> new NotFoundException("Project Not Found"));
		project = ProjectMapper.mapToProject(project, projectDto);
		project.setUpdatedAt(LocalDateTime.now());
		project = projectRepository.save(project);
		projectDto = ProjectMapper.mapToProjectDto(project, new ProjectDto());
		return projectDto;
	}

	@Override
	public ProjectDto getProjectDetails(String id) {
		Project project = projectRepository.findById(id).orElseThrow(() -> new NotFoundException("Project Not Found"));
		ProjectDto projectDto = new ProjectDto();
		projectDto = ProjectMapper.mapToProjectDto(project, projectDto);
		return projectDto;
	}

	@Override
	public List<ProjectDto> getAllProjects() {
		List<Project> projects = projectRepository.findAll();
		List<ProjectDto> projectDtos = projects.stream()
				.map(project -> ProjectMapper.mapToProjectDto(project, new ProjectDto())).collect(Collectors.toList());
		return projectDtos;
	}

}
