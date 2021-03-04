package com.capg.VehicleManagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capg.VehicleManagement.entities.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	
}
