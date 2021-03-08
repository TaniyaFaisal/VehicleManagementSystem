package com.project.main.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.main.entities.Driver;
import com.project.main.entities.Vehicle;
import com.project.main.services.VehicleService;

@RestController
@RequestMapping(path = "/api/v1")
public class VehicleController {
	@Autowired
	VehicleService vehicleService;
	
	public Vehicle addVehicle(Vehicle v) {
		return v;
		
	}
	
	public Vehicle updateVehicle(Vehicle v) {
		return v;
		
	}
	
	public Vehicle cancelVehicle(Vehicle v) {
		return v;
		
	}
	
	public Vehicle viewVehicle(Vehicle v) {
		return v;
		
	}
	
	public List<Vehicle> viewAllVehicle(Driver driver) {
		return null;
		
	}
}
