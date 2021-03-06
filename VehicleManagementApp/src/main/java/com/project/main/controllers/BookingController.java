package com.project.main.controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.project.main.entities.Booking;
import com.project.main.entities.Customer;
import com.project.main.entities.Vehicle;
import com.project.main.repositories.IBookingRepository;
import com.project.main.services.BookingService;

@RestController
@RequestMapping(path = "/api/v1")
public class BookingController {
	

	@Autowired
	IBookingRepository bookingRepository;
	
	@Autowired
	BookingService bookingService;
	
	Optional<Booking> bookings = null;

	@PostMapping("/bookings")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Booking addBooking(@RequestBody Booking b) {
		return bookingService.addBooking(b);
	}
	
	@GetMapping("/bookings")
	@ResponseStatus(code = HttpStatus.OK)
	public List<Booking> viewAllBooking(){
		return bookingService.viewAllBookings();	
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public Booking deleteBooking(@PathVariable("id") int id) {
		 return bookingService.cancelBooking(id);
	}
	
	@PutMapping("/bookings")
	@ResponseStatus(code = HttpStatus.OK)
	public Booking updateBooking(@RequestBody Booking booking) {
		return bookingService.updateBooking(booking);
	}
	
	@GetMapping("/bookingsByCustomer")
	@ResponseStatus(code = HttpStatus.OK)
	public List<Booking> viewBookingByCustomer(@RequestBody  Customer customer) {
		return bookingService.viewAllBooking(customer);
	}
	
	@GetMapping("/bookingsByVehicle")
	@ResponseStatus(code = HttpStatus.OK)
	public List<Booking> viewBookingByVehicle(@RequestBody Vehicle vehicle) {
		return bookingService.viewAllBookingByVehicle(vehicle);
	}
	
	@GetMapping("/bookingsByDate/{date}")
	@ResponseStatus(code = HttpStatus.OK)
	public List<Booking> viewBookingByDate(@PathVariable("date") 
    @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate  date) {
		return bookingService.viewAllBookingByDate(date);
	}
	
	@GetMapping("/bookings/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public Booking viewBookingById(@PathVariable("id") int id) {
		return bookingService.viewBooking(id);
	}
	
}
