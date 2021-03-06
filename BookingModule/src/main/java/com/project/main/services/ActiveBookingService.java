package com.project.main.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.main.entities.Booking;
import com.project.main.exceptions.ActiveBookingNotFoundException;
import com.project.main.repositories.IActiveBookingRepository;
import com.project.main.serviceInterfaces.IActiveBookingService;

@Service
public class ActiveBookingService implements IActiveBookingService{
	
	@Autowired
	IActiveBookingRepository iActiveBookingRepository;
	
	@Override
	public List<Booking> viewActiveBookings() {
		List<Booking> bookings = iActiveBookingRepository.findByBookedTillDate(LocalDate.now());
		if(bookings.isEmpty()) {
			throw new ActiveBookingNotFoundException("No active bookings found");
		}
		return bookings;
	}

	@Override
	public List<Booking> viewActiveBookingByBookingDate(LocalDate date) {
		List<Booking> bookings = iActiveBookingRepository.findByBookingDate(date);
		if(bookings.isEmpty()) {
			throw new ActiveBookingNotFoundException("No active bookings found");
		}
		return bookings;
	}

	@Override
	public List<Booking> viewActiveBookingBetweenDates(LocalDate from, LocalDate to) {
		List<Booking> bookings = iActiveBookingRepository.findByBookingDateAndBookedTillDate(from,to);
		if(bookings.isEmpty()) {
			throw new ActiveBookingNotFoundException("No active bookings found");
		}
		return bookings;
	}

}
