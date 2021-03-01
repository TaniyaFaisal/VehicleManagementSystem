package com.capg.services;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.capg.entities.Booking;
import com.capg.entities.Customer;
import com.capg.entities.Vehicle;
import com.capg.repositories.IBookingRepository;

public class IBookingService implements IBookingRepository{
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("Vehicle");
	EntityManager em = emf.createEntityManager();
	
	public Booking addBooking(Booking b) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(b);
		tx.commit();
		return b;
	}

	public Booking updateBooking(Booking b) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		Booking fetchCustomer = em.find(Booking.class, viewBooking(b).getBookingId());
		fetchCustomer.setDistance(b.getDistance());
		fetchCustomer.setTotalCost(b.getTotalCost());
		//similarly for other fields
		tx.commit();
		return fetchCustomer;
	}

	public Booking cancelBooking(Booking b) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		Booking fetchBooking = em.find(Booking.class, viewBooking(b).getBookingId());
		em.remove(fetchBooking);
		tx.commit();
		return fetchBooking;
	}

	public Booking viewBooking(Booking b) {
//		TypedQuery<Booking> query = em.createQuery("BookingByTotalCost",Booking.class);
		TypedQuery<Booking> query = em.createQuery("select b from  Booking b "
				+ " where b.totalCost = :totalCost",Booking.class);
		query.setParameter("totalCost",b.getTotalCost());
		return query.getSingleResult();
	}

	public List<Booking> viewAllBooking(Customer customer) {
		TypedQuery<Booking> bookings = em.createQuery("select b from  Booking b "
				+ " where b.customer = :customer",Booking.class);
		ICustomerService customerService = new ICustomerService();
		bookings.setParameter("customer", customerService.viewCustomer(customer));
		return bookings.getResultList();
	}

	public List<Booking> viewAllBookings(Vehicle v) {
		TypedQuery<Booking> bookings = em.createQuery("select b from  Booking b inner join b.vehicle v "
				+ " where v.vehicleNumber = :vehicleNumber",Booking.class);
		bookings.setParameter("vehicleNumber", v.getVehicleNumber());
		return bookings.getResultList();
	}

	public List<Booking> viewAllBookingByDate(LocalDate bdate) {
		TypedQuery<Booking> bookings = em.createQuery("select b from  Booking b "
				+ " where b.bookingDate = :bdate",Booking.class);
		bookings.setParameter("bdate", bdate);
		return bookings.getResultList();
	}
}
