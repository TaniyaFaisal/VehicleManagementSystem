package com.project.main.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.main.entities.Driver;
import com.project.main.entities.Vehicle;
import com.project.main.exceptions.VehicleNotFoundException;
import com.project.main.repositories.IDriverRepository;
import com.project.main.repositories.IVehicleRepository;
import com.project.main.serviceInterfaces.IVehicleService;

@Service
public class VehicleService implements IVehicleService{

	@Autowired
	IVehicleRepository vehicleRepository;
	
	@Autowired
	IDriverRepository driverRepository;
	
	@Override
	@Transactional
	public Vehicle addVehicle(Vehicle v) {
		try {
			Optional<Driver> driver = driverRepository.findById(v.getDriver().getDriverId());
			if(driver != null) {
				v.setDriver(driver.get());
			}
		}catch (VehicleNotFoundException e) {
			e.getMessage();
		}	
		Vehicle vehicle = vehicleRepository.save(v);
		return vehicle;
	}

	@Override
	@Transactional
	public Vehicle updateVehicle(Vehicle vehicle) {
		Vehicle v = viewVehicle(vehicle.getVehicleId());
		v.setFixedCharges(vehicle.getFixedCharges());
		return v;
	}

	@Override
	public Vehicle cancelVehicle(int id) {
		Optional<Vehicle> vehicle = vehicleRepository.findById(id);
		Vehicle v = null;
		if(vehicle.isPresent()){
			v = vehicle.get(); 
			vehicleRepository.delete(v);
		}
		return v;
	}

	@Override
	public Vehicle viewVehicle(int  id) {
		Optional<Vehicle> vehicle = vehicleRepository.findById(id);
		if(vehicle.isEmpty()){
			throw new VehicleNotFoundException("Vehicle with id " +id +" not found");
 		}
		return vehicle.get();
	}

	@Override
	public List<Vehicle> viewAllVehicle(Driver driver) {
		List<Vehicle> vehicles = vehicleRepository.findVehicleByDriver(driver);
		return vehicles;
	}

}
