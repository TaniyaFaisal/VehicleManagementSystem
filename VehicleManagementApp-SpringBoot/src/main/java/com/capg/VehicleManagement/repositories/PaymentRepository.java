package com.capg.VehicleManagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capg.VehicleManagement.entities.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer>{

}