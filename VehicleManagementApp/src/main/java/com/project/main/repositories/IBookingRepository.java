package com.project.main.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.main.entities.Booking;
import com.project.main.entities.Customer;
import com.project.main.entities.Vehicle;

@Repository
public interface IBookingRepository extends JpaRepository<Booking, Integer>{

	public List<Booking> findByCustomer(Customer customer);

	public List<Booking> findByVehicle(Vehicle vehicle);

	public List<Booking> findByBookingDate(LocalDate bDate);
}
