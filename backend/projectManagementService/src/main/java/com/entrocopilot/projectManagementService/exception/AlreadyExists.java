package com.entrocopilot.projectManagementService.exception;

public class AlreadyExists extends RuntimeException{
	public AlreadyExists(String message) {
		super(message);
	}
}
