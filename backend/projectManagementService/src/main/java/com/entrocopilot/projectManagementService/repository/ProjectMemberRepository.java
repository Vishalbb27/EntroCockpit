package com.entrocopilot.projectManagementService.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.entrocopilot.projectManagementService.model.Project;
import com.entrocopilot.projectManagementService.model.ProjectMember;

public interface ProjectMemberRepository extends MongoRepository<ProjectMember, String> {
	List<ProjectMember> findAllByProject(Project id);
	
	Boolean existsByEmployeeId(String id);
}
