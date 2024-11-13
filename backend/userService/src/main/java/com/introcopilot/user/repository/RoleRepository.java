package com.introcopilot.user.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.introcopilot.user.model.Role;

public interface RoleRepository extends MongoRepository<Role, String> {
	Role findByName(String name);
}
