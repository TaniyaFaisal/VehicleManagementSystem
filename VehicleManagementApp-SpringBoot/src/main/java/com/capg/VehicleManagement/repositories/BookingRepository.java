package com.capg.VehicleManagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capg.VehicleManagement.entities.Booking;

public interface BookingRepository extends JpaRepository<Booking, Integer>{

}
