package com.capg.VehicleManagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capg.VehicleManagement.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{

}
