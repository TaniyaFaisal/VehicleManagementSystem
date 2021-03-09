package com.project.main.controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
	public ResponseEntity<Booking> addBooking(@RequestBody Booking b) {
		Booking booking = bookingService.addBooking(b);
		return new ResponseEntity<>(booking,HttpStatus.CREATED);
	}
	
	@GetMapping("/bookings")
	public ResponseEntity<List<Booking>> viewAllBooking(){
		List<Booking> bookings = bookingService.viewAllBookings();	
		return new ResponseEntity<>(bookings,HttpStatus.OK);
	}
	
	@DeleteMapping("bookings/{id}")
	public ResponseEntity<String> deleteBooking(@PathVariable("id") int id) {
		bookingService.cancelBooking(id);
		return new ResponseEntity<>("Successfuly deleted", HttpStatus.OK);
	}
	
	@PutMapping("/bookings")
	public ResponseEntity<String> updateBooking(@RequestBody Booking booking) {
		bookingService.updateBooking(booking);
		return new ResponseEntity<>("Successfuly updated", HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/bookingsByCustomer")
	public ResponseEntity<List<Booking>> viewBookingByCustomer(@RequestBody  Customer customer) {
		List<Booking> bookings = bookingService.viewAllBooking(customer);
		return new ResponseEntity<>(bookings,HttpStatus.OK);
	}
	
	@GetMapping("/bookingsByVehicle")
	public ResponseEntity<List<Booking>> viewBookingByVehicle(@RequestBody Vehicle vehicle) {
		List<Booking> bookings = bookingService.viewAllBookingByVehicle(vehicle);
		return new ResponseEntity<>(bookings,HttpStatus.OK);
	}
	
	@GetMapping("/bookingsByDate/{date}")
	public ResponseEntity<List<Booking>> viewBookingByDate(@PathVariable("date") 
    @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate  date) {
		List<Booking> bookings = bookingService.viewAllBookingByDate(date);
		return new ResponseEntity<>(bookings,HttpStatus.OK);
	}
	
	@GetMapping("/bookings/{id}")
	public ResponseEntity<Booking> viewBookingById(@PathVariable("id") int id) {
		Booking booking = bookingService.viewBooking(id);
		return new ResponseEntity<>(booking,HttpStatus.OK);
	}
	
}
