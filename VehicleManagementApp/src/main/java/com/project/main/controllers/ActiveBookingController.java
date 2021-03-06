package com.project.main.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.project.main.entities.Booking;
import com.project.main.services.ActiveBookingService;

@RestController
@RequestMapping(path = "/api/v1")
public class ActiveBookingController {
	
	@Autowired
	ActiveBookingService activeBookingService;
	
	@GetMapping("/activeBookings")
	@ResponseStatus(code = HttpStatus.OK)
	public List<Booking> viewActiveBookings() {
		return activeBookingService.viewActiveBookings();
	}
	
	@GetMapping("/activeBookings/{date}")
	@ResponseStatus(code = HttpStatus.OK)
	public List<Booking> viewActiveBookingByBookingDate(@PathVariable("date") 
    @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate  date) {
		return activeBookingService.viewActiveBookingByBookingDate(date);
	}
	
	@GetMapping("/activeBookings/{from}/{to}")
	@ResponseStatus(code = HttpStatus.OK)
	public List<Booking> viewActiveBookingBetweenDates(@PathVariable("from") 
    @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate  from, @PathVariable("to") 
    @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate  to) {
		return activeBookingService.viewActiveBookingBetweenDates(from, to);
	}
	
}
