package com.entrocopilot.projectManagementService.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.entrocopilot.projectManagementService.model.Project;

public interface ProjectRepository extends MongoRepository<Project, String> {
	Boolean existsByprojectName(String id);
}
