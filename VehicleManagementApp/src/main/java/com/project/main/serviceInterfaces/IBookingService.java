package com.project.main.serviceInterfaces;

import java.time.LocalDate;
import java.util.List;

import com.project.main.entities.Booking;
import com.project.main.entities.Customer;
import com.project.main.entities.Vehicle;

public interface IBookingService {
	public Booking addBooking(Booking booking);
	public Booking cancelBooking(int id);
	public Booking updateBooking(Booking b);
	public Booking viewBooking(int id);
	public List<Booking> viewAllBooking(Customer customer);
	public List<Booking> viewAllBookingByDate(LocalDate bookingDate);
	public List<Booking> viewAllBookingByVehicle(Vehicle vehicle); 
	
}
