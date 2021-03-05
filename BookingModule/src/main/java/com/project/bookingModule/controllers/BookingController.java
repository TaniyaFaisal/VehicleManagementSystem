package com.project.bookingModule.controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.project.bookingModule.entities.Booking;
import com.project.bookingModule.repositories.IBookingRepository;
import com.project.bookingModule.services.BookingService;

@RestController
@RequestMapping(path = "/api/v1/")
public class BookingController {
	

	@Autowired
	IBookingRepository bookingRepository;
	
	@Autowired
	BookingService bookingService;
	
	Optional<Booking> bookings = null;

	@PostMapping("/bookings")
	@ResponseStatus(code = HttpStatus.CREATED)
	public void addBooking(@RequestBody Booking b) {
		bookingService.addBooking(b);
	}
	
	@GetMapping("/bookings")
	@ResponseStatus(code = HttpStatus.OK)
	public List<Booking> viewAllBooking(){
		return bookingService.viewAllBookings();
		
	}
	
//	@GetMapping("/bookings/{id}")
//	@ResponseStatus(code = HttpStatus.OK)
//	public Booking viewBookingById(@PathVariable("id") int id) {
//		return bookingService.viewBooking(id);
//	}
//	
//	@DeleteMapping("/bookings/{id}")
//	@ResponseStatus(code = HttpStatus.OK)
//	public void deleteBooking(@PathVariable("id") int id) {
//		 bookingService.deleteBooking(id);
//	}
	
	@PutMapping("/bookings")
	@ResponseStatus(code = HttpStatus.OK)
	public void updateBooking(@RequestBody Booking booking) {
		bookingService.updateBooking(booking);
	}
	
//	@GetMapping("/bookingsByCustomer/{name}")
//	@ResponseStatus(code = HttpStatus.OK)
//	public List<Booking> viewBookingByCustomer(@PathVariable("name") String name) {
//		return bookingService.viewBookingByCustomer(name);
//	}
//	
//	@GetMapping("/bookingsByVehicle/{name}")
//	@ResponseStatus(code = HttpStatus.OK)
//	public List<Booking> viewBookingByVehicle(@PathVariable("name") String number) {
//		return bookingService.viewBookingByVehicle(number);
//	}
	
	@GetMapping("/bookingsByDate/{date}")
	@ResponseStatus(code = HttpStatus.OK)
	public List<Booking> viewBookingByDate(@PathVariable("date") 
    @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate  date) {
		return bookingService.viewAllBookingByDate(date);
	}
}
