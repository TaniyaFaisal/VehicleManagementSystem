package com.project.main.exceptions;

public class UserNotFoundException extends RuntimeException{
	public UserNotFoundException() {
		// TODO Auto-generated constructor stub
	}
	
	public UserNotFoundException(String message) {
		super(message);
	}
}
