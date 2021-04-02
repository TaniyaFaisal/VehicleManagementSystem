package com.example.vehiclemgmt.serviceInterfaces;

import java.time.LocalDate;
import java.util.List;

import com.example.vehiclemgmt.entities.Booking;


public interface IActiveBookingService {
	public List<Booking> viewActiveBookings();
	public List<Booking> viewActiveBookingByBookingDate(LocalDate date);
	public List<Booking> viewActiveBookingBetweenDates(LocalDate from, LocalDate to);
}
