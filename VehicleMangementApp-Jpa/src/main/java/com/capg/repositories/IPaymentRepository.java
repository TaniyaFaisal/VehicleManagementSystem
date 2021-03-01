package com.capg.repositories;

import java.time.LocalDate;
import java.util.List;

import com.capg.entities.Booking;
import com.capg.entities.Payment;
import com.capg.entities.Vehicle;

public interface IPaymentRepository {
	public Payment addPayment(Payment payment);
	public Payment cancelPayment(int id);
	public Payment viewPayment(Booking booking);
	public List<Payment> viewAllPayment(Vehicle vehicle);
	public double calculateMonthlyRevenue(LocalDate d1, LocalDate d2);
	public double calculateTotalPayment(Vehicle vehicle);
}
