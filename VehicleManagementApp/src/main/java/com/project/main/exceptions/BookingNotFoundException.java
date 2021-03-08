package com.project.main.exceptions;

public class BookingNotFoundException extends RuntimeException{


	public BookingNotFoundException() {
		
	}
	
	public BookingNotFoundException(String message) {
		super(message);
	}
}
