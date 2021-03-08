package com.project.main.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.main.entities.Booking;
import com.project.main.entities.Payment;
import com.project.main.entities.Vehicle;
import com.project.main.exceptions.PaymentException;
import com.project.main.exceptions.PaymentNotFoundException;
import com.project.main.repositories.IBookingRepository;
import com.project.main.repositories.IPaymentRepository;
import com.project.main.serviceInterfaces.IPaymentService;

@Service
public class PaymentService implements IPaymentService{

	@Autowired
	IPaymentRepository paymentRepository;

	@Autowired
	IBookingRepository bookingRepository;

	@Override
	@Transactional
	public Payment addPayment(Payment p) {
		try {
			Optional<Booking> booking = bookingRepository.findById(p.getBooking().getBookingId());		
			if(booking != null) {
				p.setBooking(booking.get());
			}
		}catch(PaymentException e) {
			e.getMessage();	
		}		
		paymentRepository.save(p);
		return p;
	}

	@Override
	public Payment cancelPayment(int id) {
		Optional<Payment> payment = paymentRepository.findById(id);
		if(payment.isEmpty()){
			throw new PaymentNotFoundException("Payment with id " +id +" not found");
 		}
		Payment p = payment.get(); 
		paymentRepository.delete(p);	
		return p;
	}

	@Override
	public Payment viewPayment(Booking booking) {
		Payment payment = paymentRepository.findPaymentsByBooking(booking);
		return payment;
	}

	@Override
	public List<Payment> viewAllPayment(Vehicle vehicle) {
		List<Payment> payments = paymentRepository.findPaymentByVehicle(vehicle);
		if(payments.isEmpty()) {
			throw new PaymentNotFoundException("No payments found");
		}
		return payments;
	}

	@Override
	public double calculateMonthlyRevenue(LocalDate d1, LocalDate d2) {
		double revenue = paymentRepository.findTotalPaymentByDates(d1, d2);
		return revenue;
	}

	@Override
	public double calculateTotalPayment(Vehicle vehicle) {
		double totalPayment = paymentRepository.findTotalPaymentByVehicle(vehicle);
		return totalPayment;
	}
	
	public List<Payment> viewPayments(){	
		List<Payment> payments = paymentRepository.findAll();
		if(payments.isEmpty()) {
			throw new PaymentNotFoundException("No payments found");
		}
		return payments;
	}

}
