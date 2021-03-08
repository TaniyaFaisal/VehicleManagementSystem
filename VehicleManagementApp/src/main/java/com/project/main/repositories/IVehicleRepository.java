package com.project.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.main.entities.Vehicle;

@Repository
public interface IVehicleRepository extends JpaRepository<Vehicle, Integer>{

	public Vehicle findVehicleByVehicleNumber(String number);

}
