package com.project.main.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.project.main.entities.Booking;
import com.project.main.entities.Payment;
import com.project.main.entities.Vehicle;
import com.project.main.repositories.IBookingRepository;
import com.project.main.repositories.IPaymentRepository;
import com.project.main.services.PaymentService;

@RestController
@RequestMapping(path = "/api/v1")
public class PaymentController {
	@Autowired
	IPaymentRepository paymentRepository;
	@Autowired
	IBookingRepository bookingRepository;

	@Autowired
	PaymentService paymentService;

	@PostMapping("/payments")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Payment addPayment(@RequestBody Payment p) {
		return paymentService.addPayment(p);
	}

	@DeleteMapping("/payments/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public void cancelPayment(@PathVariable("id") int id) {
		paymentService.cancelPayment(id);
	}

	@GetMapping("/payments")
	@ResponseStatus(code = HttpStatus.OK)
	public List<Payment> viewPayments(){
		return paymentService.viewPayments();
	}
	
	
	@GetMapping("/paymentByBooking")
	@ResponseStatus(code = HttpStatus.OK)
	public Payment viewPaymentByBooking(@RequestBody Booking booking){
		return paymentService.viewPayment(booking);
	}

	@GetMapping("/paymentsByVehicle")
	@ResponseStatus(code = HttpStatus.OK)
	public List<Payment> findPaymentsByVehicle(@RequestBody Vehicle vehicle) {
		return paymentService.viewAllPayment(vehicle);
	}

	@GetMapping("/totalPaymentByVehicle")
	@ResponseStatus(code = HttpStatus.OK)
	public double calculateTotalPaymentByVehicle(@RequestBody Vehicle vehicle) {
		return paymentService.calculateTotalPayment(vehicle);
	}

	@GetMapping("/calculateMonthlyRevenue/{date1}/{date2}")
	@ResponseStatus(code = HttpStatus.OK)
	public double calculateTMonthlyRevenue(@PathVariable("date1") 
	@DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate  date1, @PathVariable("date2") 
	@DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate  date2) {
		return paymentService.calculateMonthlyRevenue(date1,date2);
	}
}