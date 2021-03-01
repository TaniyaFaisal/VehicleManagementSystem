package com.capg.repositories;

import java.time.LocalDate;
import java.util.List;

import com.capg.entities.Booking;
import com.capg.entities.Customer;
import com.capg.entities.Vehicle;

public interface IBookingRepository {
	public Booking addBooking(Booking b);
	public Booking updateBooking(Booking b);
	public Booking cancelBooking(Booking b);
	public Booking viewBooking(Booking b);
	public List<Booking> viewAllBooking(Customer customer) ;
	public List<Booking> viewAllBookings(Vehicle v);
	public List<Booking> viewAllBookingByDate(LocalDate bdate);
	
}
