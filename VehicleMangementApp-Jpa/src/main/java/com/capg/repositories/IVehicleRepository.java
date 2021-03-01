package com.capg.repositories;

import java.util.List;

import com.capg.entities.Driver;
import com.capg.entities.Vehicle;

public interface IVehicleRepository {
	public Vehicle addVehicle(Vehicle v);
	public Vehicle updateVehicle(Vehicle v);
	public Vehicle cancelVehicle(Vehicle v);
	public Vehicle viewVehicle(Vehicle v);
	public List<Vehicle> viewAllVehicle(Driver driver) ;
	
}
