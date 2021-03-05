package com.project.bookingModule.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.bookingModule.entities.Customer;

public interface ICustomerRepository extends JpaRepository<Customer, Integer>{

	public Customer findCustomerByFirstName(String name);

}
