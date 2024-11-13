package com.entrocopilot.employeeManagementService.exception;

public class AlreadyExists extends RuntimeException{
	public AlreadyExists(String message) {
		super(message);
	}
}
