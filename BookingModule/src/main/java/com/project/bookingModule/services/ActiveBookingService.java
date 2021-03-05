package com.project.bookingModule.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.bookingModule.entities.Booking;
import com.project.bookingModule.repositories.IActiveBookingRepository;
import com.project.bookingModule.serviceInterfaces.IActiveBookingService;

@Service
public class ActiveBookingService implements IActiveBookingService{
	
	@Autowired
	IActiveBookingRepository iActiveBookingRepository;
	
	@Override
	public List<Booking> viewActiveBookings() {
		List<Booking> bookings = iActiveBookingRepository.findByBookedTillDate(LocalDate.now());
		return bookings;
	}

	@Override
	public List<Booking> viewActiveBookingByDate(LocalDate date) {
		List<Booking> bookings = iActiveBookingRepository.findByBookedTillDate(LocalDate.of(2021,03,07));
		return bookings;
	}

	@Override
	public List<Booking> viewActiveBookingBetweenDates(LocalDate from, LocalDate to) {
		List<Booking> bookings = iActiveBookingRepository.findByBookingDateAndBookedTillDate(LocalDate.of(2021,03,04),LocalDate.of(2021,03,06));
		return bookings;
	}

}
