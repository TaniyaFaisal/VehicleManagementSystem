package com.project.main.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.main.entities.Driver;
import com.project.main.entities.Vehicle;

@Repository
public interface IVehicleRepository extends JpaRepository<Vehicle, Integer>{

	public Vehicle findVehicleByVehicleNumber(String number);
	
	public List<Vehicle> findVehicleByDriver(Driver driver);

}
