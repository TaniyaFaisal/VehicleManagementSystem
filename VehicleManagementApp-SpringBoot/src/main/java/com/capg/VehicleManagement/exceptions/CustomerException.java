package com.capg.VehicleManagement.exceptions;

public class CustomerException extends RuntimeException{

	public CustomerException() {
	}
	
	public CustomerException(String message) {
		super(message);
	}
}
