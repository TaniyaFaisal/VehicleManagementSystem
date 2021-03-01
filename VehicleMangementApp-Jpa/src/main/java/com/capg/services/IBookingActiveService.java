package com.capg.services;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.capg.entities.Booking;
import com.capg.repositories.IBookingActiveRepository;

public class IBookingActiveService implements IBookingActiveRepository{
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("Vehicle");
	EntityManager em = emf.createEntityManager();
	 
	public List<Booking> viewActiveBookings() {
		TypedQuery<Booking> query = em.createQuery("select b from Booking where b.bookedTillDate <= :bDate",Booking.class);
		query.setParameter("bDate", LocalDate.now());
		return query.getResultList();
	}

	public List<Booking> viewActiveBookingsByDate(LocalDate from, LocalDate to) {
		TypedQuery<Booking> query = em.createQuery("select b from Booking where b.bookingDate >= :from and "
				+ " b.bookedTillDate <= :to ",Booking.class);
		query.setParameter("from", from);
		query.setParameter("to", to);
		return query.getResultList();
	}

	//add more methods if required
}
