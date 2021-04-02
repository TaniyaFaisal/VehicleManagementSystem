package com.example.vehiclemgmt.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.vehiclemgmt.entities.Booking;
import com.example.vehiclemgmt.entities.Payment;
import com.example.vehiclemgmt.entities.Vehicle;


@Repository
public interface IPaymentRepository extends JpaRepository<Payment, Integer>{
		
	public Payment findPaymentsByBooking(Booking booking);

	@Query("select p from  Payment p inner join p.booking b  where b.vehicle = ?1")
	public List<Payment> findPaymentByVehicle(Vehicle vehicle);
	
	@Query("select sum(b.totalCost) from  Payment p inner join p.booking b where b.vehicle = ?1")
    public double findTotalPaymentByVehicle(Vehicle vehicle);
	
	@Query("select sum(b.totalCost) from  Payment p inner join p.booking b where p.paymentDate >= ?1 and p.paymentDate <= ?2")
    public double findTotalPaymentByDates(LocalDate d1, LocalDate d2);
}
