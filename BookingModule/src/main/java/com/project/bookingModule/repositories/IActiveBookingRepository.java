package com.project.bookingModule.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.bookingModule.entities.Booking;

@Repository
public interface IActiveBookingRepository extends JpaRepository<Booking	, Integer>{
	@Query("SELECT b FROM Booking b WHERE b.bookedTillDate >= ?1")
    List<Booking> findByBookedTillDate(LocalDate bookedTillDate);
	
	@Query("SELECT b FROM Booking b WHERE b.bookingDate = ?1")
    List<Booking> findByBookingDate(LocalDate bookingDate);
	
	@Query("SELECT b FROM Booking b WHERE b.bookedTillDate = ?1")
    List<Booking> findByBookingDateAndBookedTillDate(LocalDate bookingDate,LocalDate bookedTillDate);
}
