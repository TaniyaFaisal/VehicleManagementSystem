package com.example.vehiclemgmt.serviceInterfaces;

import java.util.List;

import com.example.vehiclemgmt.entities.Driver;
import com.example.vehiclemgmt.entities.Vehicle;

public interface IVehicleService {
	public Vehicle addVehicle(Vehicle v);
	public Vehicle updateVehicle(Vehicle v);
	public Vehicle cancelVehicle(int id);
	public Vehicle viewVehicle(int id);
	public List<Vehicle> viewAllVehicle(Driver driver) ;
}
