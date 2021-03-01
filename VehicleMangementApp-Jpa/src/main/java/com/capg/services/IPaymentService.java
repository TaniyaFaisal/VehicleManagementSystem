package com.capg.services;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.capg.entities.Booking;
import com.capg.entities.Payment;
import com.capg.entities.Vehicle;
import com.capg.repositories.IPaymentRepository;

public class IPaymentService implements IPaymentRepository{
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("Vehicle");
	EntityManager em = emf.createEntityManager();
	
	public Payment addPayment(Payment payment) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(payment);
		tx.commit();
		return payment;
	}

	public Payment cancelPayment(int id) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		Payment fetchPayment = em.find(Payment.class, id);
		em.remove(fetchPayment);
		tx.commit();
		return fetchPayment;
	}

	public Payment viewPayment(Booking booking) {
		TypedQuery<Payment> bookings = em.createQuery("select p from  Payment p "
				+ " where p.booking = :booking",Payment.class);
		IBookingService bookingService = new IBookingService();
		bookings.setParameter("booking", bookingService.viewBooking(booking));
		return bookings.getSingleResult();
		
	}

	public List<Payment> viewAllPayment(Vehicle vehicle) {
		TypedQuery<Payment> payments = em.createQuery("select p from  Payment p inner join p.booking b"
				+ " where b.vehicle = :vehicle",Payment.class);
		IVehicleService vehicleService = new IVehicleService();
		payments.setParameter("vehicle", vehicleService.viewVehicle(vehicle));
		return payments.getResultList();
	}

	public double calculateMonthlyRevenue(LocalDate d1, LocalDate d2) {
		TypedQuery<Double> bookings = em.createQuery("select sum(b.totalCost) from  p.booking b inner join Payment p"
				+ " where p.paymentDate >= :d1 and p.paymentDate <= :d2",Double.class);
		bookings.setParameter("d1", d1);
		bookings.setParameter("d2", d2);
		return bookings.getSingleResult();
	}

	public double calculateTotalPayment(Vehicle vehicle) {
		TypedQuery<Double> payments = em.createQuery("select sum(b.totalCost) from Payment p inner join p.booking b"
				+ " where b.vehicle = :vehicle",Double.class);
		IVehicleService vehicleService = new IVehicleService();
		payments.setParameter("vehicle", vehicleService.viewVehicle(vehicle));
		return payments.getSingleResult();
	}
}
