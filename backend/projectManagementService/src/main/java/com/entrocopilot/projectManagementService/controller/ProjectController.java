package com.entrocopilot.projectManagementService.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.entrocopilot.projectManagementService.dto.ProjectDto;
import com.entrocopilot.projectManagementService.service.ProjectService;

@RestController
@RequestMapping("/api/project")
public class ProjectController {
	
	private ProjectService projectService;

	public ProjectController(ProjectService projectService) {
		super();
		this.projectService = projectService;
	}
	
	@PostMapping
	public ResponseEntity<ProjectDto> createProject(@RequestBody ProjectDto projectDto){
		ProjectDto projeDto = projectService.createProject(projectDto);
		return new ResponseEntity<ProjectDto>(projeDto,HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<ProjectDto> updateProject(@RequestBody ProjectDto projectDto){
		ProjectDto projeDto = projectService.updateProject(projectDto);
		return new ResponseEntity<ProjectDto>(projeDto,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/id")
	public ResponseEntity<ProjectDto> getProjectById(@RequestParam("id") String id){
		ProjectDto projeDto = projectService.getProjectDetails(id);
		return new ResponseEntity<ProjectDto>(projeDto,HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<ProjectDto>> getAllProjects(){
		List<ProjectDto> projeDto = projectService.getAllProjects();
		return new ResponseEntity<List<ProjectDto>>(projeDto,HttpStatus.OK);
	}
}
