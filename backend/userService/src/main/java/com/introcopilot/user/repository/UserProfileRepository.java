package com.introcopilot.user.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.introcopilot.user.model.User;
import com.introcopilot.user.model.UserProfile;

public interface UserProfileRepository extends MongoRepository<UserProfile, String> {
	UserProfile findByUser(User user);
	
}
