package com.project.main.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.main.entities.Customer;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Integer>{

	public Customer findCustomerByFirstName(String name);
	
	public Customer findCustomerByFirstNameAndLastName(String fname, String lname);
	
	@Query("select b.customer from  Booking b inner join b.vehicle v where v.type =  ?1")
    public List<Customer> findByVehicleType(String vtype);
	
	@Query("select b.customer from  Booking b inner join b.vehicle v where v.location =  ?1")
    public List<Customer> findByVehicleLocation(String location);

}
