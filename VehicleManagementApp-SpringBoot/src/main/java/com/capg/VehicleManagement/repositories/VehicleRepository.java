package com.capg.VehicleManagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capg.VehicleManagement.entities.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Integer>{

}
