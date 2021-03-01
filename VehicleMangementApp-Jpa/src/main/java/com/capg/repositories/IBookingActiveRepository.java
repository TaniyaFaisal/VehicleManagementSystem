package com.capg.repositories;

import java.time.LocalDate;
import java.util.List;

import com.capg.entities.Booking;

public interface IBookingActiveRepository {
	public List<Booking> viewActiveBookings();
	public List<Booking> viewActiveBookingsByDate(LocalDate from, LocalDate to);
	
}
