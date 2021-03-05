package com.project.bookingModule.serviceInterfaces;

import java.time.LocalDate;
import java.util.List;

import com.project.bookingModule.entities.Booking;
import com.project.bookingModule.entities.Customer;
import com.project.bookingModule.entities.Vehicle;

public interface IBookingService {
	public Booking addBooking(Booking booking);
	public Booking cancelBooking(Booking b);
	public Booking updateBooking(Booking b);
	public Booking viewBooking(Booking b);
	public List<Booking> viewAllBooking(Customer customer);
	public List<Booking> viewAllBookingByDate(LocalDate bookingDate);
	public List<Booking> viewAllBookingByVehicle(Vehicle vehicle); 
	
}
