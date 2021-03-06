package com.project.main.exceptions;

public class ActiveBookingNotFoundException extends RuntimeException{

	public ActiveBookingNotFoundException() {
		
	}
	
	public ActiveBookingNotFoundException(String message) {
		super(message);
	}
}
