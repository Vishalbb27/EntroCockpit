package com.introcopilot.user.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.introcopilot.user.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
	Optional<User> findByUsername(String username);
    
    Boolean existsByEmail(String email);
    
    Optional<User> findByUsernameOrEmail(String username, String email);
    
    Boolean existsByUsername(String username);
}
