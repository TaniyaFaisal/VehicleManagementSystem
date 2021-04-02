package com.example.vehiclemgmt.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.vehiclemgmt.entities.Booking;
import com.example.vehiclemgmt.entities.Customer;
import com.example.vehiclemgmt.entities.Vehicle;



@Repository
public interface IBookingRepository extends JpaRepository<Booking, Integer>{

	public List<Booking> findByCustomer(Customer customer);

	public List<Booking> findByVehicle(Vehicle vehicle);

	public List<Booking> findByBookingDate(LocalDate bDate);
}
