package com.project.bookingModule.serviceInterfaces;

import java.time.LocalDate;
import java.util.List;

import com.project.bookingModule.entities.Booking;

public interface IActiveBookingService {
	public List<Booking> viewActiveBookings();
	public List<Booking> viewActiveBookingByDate(LocalDate date);
	public List<Booking> viewActiveBookingBetweenDates(LocalDate from, LocalDate to);
}
