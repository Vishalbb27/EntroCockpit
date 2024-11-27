package com.entrocopilot.attendanceService.exception;

public class AlreadyExists extends RuntimeException{
	public AlreadyExists(String message) {
		super(message);
	}
}
