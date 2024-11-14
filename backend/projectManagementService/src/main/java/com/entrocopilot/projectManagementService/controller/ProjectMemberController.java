package com.entrocopilot.projectManagementService.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.entrocopilot.projectManagementService.dto.ProjectMemberDto;
import com.entrocopilot.projectManagementService.service.ProjectMemberService;

@RestController
@RequestMapping("/api/project/projectMember")
public class ProjectMemberController {
	private ProjectMemberService projectMemberService;

	public ProjectMemberController(ProjectMemberService projectMemberService) {
		super();
		this.projectMemberService = projectMemberService;
	}
	
	@PostMapping
	public ResponseEntity<ProjectMemberDto> createProjectMember(@RequestBody ProjectMemberDto projectMemberDto){
		ProjectMemberDto prDto = projectMemberService.createProjectMember(projectMemberDto);
		return new ResponseEntity<ProjectMemberDto>(prDto,HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<ProjectMemberDto> updateProjectMember(@RequestBody ProjectMemberDto projectMemberDto){
		ProjectMemberDto prDto = projectMemberService.updateProjectMember(projectMemberDto);
		return new ResponseEntity<ProjectMemberDto>(prDto,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/project")
	public ResponseEntity<List<ProjectMemberDto>> getProjectMemeberByProjectId(@RequestParam("id") String id){
		List<ProjectMemberDto> projectMemberDtos = projectMemberService.getProjectMembersByProjectId(id);
		return new ResponseEntity<List<ProjectMemberDto>>(projectMemberDtos,HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping
	public ResponseEntity<ProjectMemberDto> deleteProjectMember(@RequestParam("id") String id){
		projectMemberService.deleteProjectMember(id);
		return new ResponseEntity<ProjectMemberDto>(HttpStatus.OK);
	}
}
