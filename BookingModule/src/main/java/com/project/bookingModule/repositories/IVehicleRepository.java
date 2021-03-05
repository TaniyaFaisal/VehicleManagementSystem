package com.project.bookingModule.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.bookingModule.entities.Vehicle;

public interface IVehicleRepository extends JpaRepository<Vehicle, Integer>{

	public Vehicle findVehicleByVehicleNumber(String number);

}
