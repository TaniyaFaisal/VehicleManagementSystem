package com.project.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.main.entities.Vehicle;

public interface IVehicleRepository extends JpaRepository<Vehicle, Integer>{

	public Vehicle findVehicleByVehicleNumber(String number);

}
