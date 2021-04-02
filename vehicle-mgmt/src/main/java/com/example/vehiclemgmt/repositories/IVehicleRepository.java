package com.example.vehiclemgmt.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.vehiclemgmt.entities.Driver;
import com.example.vehiclemgmt.entities.Vehicle;




@Repository
public interface IVehicleRepository extends JpaRepository<Vehicle, Integer>{

	public Vehicle findVehicleByVehicleNumber(String number);
	
	public List<Vehicle> findVehicleByDriver(Driver driver);

}
