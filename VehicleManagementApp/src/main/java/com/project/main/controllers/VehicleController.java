package com.project.main.controllers;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.project.main.entities.Driver;
import com.project.main.entities.Vehicle;
import com.project.main.services.VehicleService;

@RestController
@RequestMapping(path = "/api/v1")
public class VehicleController {
	
	@Autowired
	VehicleService vehicleService;
	
	@PostMapping("/vehicles")
	@ResponseStatus(code = HttpStatus.CREATED)
	public void addVehicle(@RequestBody Vehicle v) {
		vehicleService.addVehicle(v);
	}
	
	@PutMapping("/vehicles")
	@ResponseStatus(code = HttpStatus.OK)
	@Transactional
	public void updateVehicle(@RequestBody Vehicle v) {
		vehicleService.updateVehicle(v);
	}
	
	@DeleteMapping("/vehicles/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public void deleteCustomer(@PathVariable("id") int id) {
		vehicleService.cancelVehicle(id);
	}
	
	@GetMapping("/vehicles/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public Vehicle viewVehicle(@PathVariable("id") int id) {
		Vehicle vehicle = vehicleService.viewVehicle(id);
		return vehicle;
		
	}
	
	@GetMapping("/vehicles")
	@ResponseStatus(code = HttpStatus.OK)
	public List<Vehicle> viewAllVehicle(@RequestBody Driver driver) {
		List<Vehicle> vehicles = vehicleService.viewAllVehicle(driver);
		return vehicles;
		
	}
}
