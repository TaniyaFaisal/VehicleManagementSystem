package com.project.main.exceptions;

public class BookingException extends RuntimeException{

	public BookingException() {
		
	}
	
	public BookingException(String message) {
		super(message);
	}
}
