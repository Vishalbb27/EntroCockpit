package com.introcopilot.user.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "role")
public class Role {
	
	@Id
	private String id;
	private String name;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Role(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public Role() {
		
	}
	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + "]";
	}
	
}
