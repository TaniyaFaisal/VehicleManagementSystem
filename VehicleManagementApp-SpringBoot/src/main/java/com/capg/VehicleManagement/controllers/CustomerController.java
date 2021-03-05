package com.capg.VehicleManagement.controllers;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capg.VehicleManagement.entities.Customer;
import com.capg.VehicleManagement.exceptions.CustomerException;
import com.capg.VehicleManagement.repositories.CustomerRepository;


@RestController
@RequestMapping(path = "/api/v1/customers")
public class CustomerController {
	
	@Autowired
	CustomerRepository customerRepository;
	
	@PostMapping("/")
	public ResponseEntity<Void> addCustomer(@RequestBody Customer customer) {	
		customerRepository.save(customer);
		ResponseEntity<Void>  re = new ResponseEntity<Void>(HttpStatus.CREATED);	
		return re;
	}
	
	@GetMapping("/")
	public ResponseEntity<List<Customer>> viewAllCustomers(){
		ResponseEntity<List<Customer>> re = new ResponseEntity<>(customerRepository.findAll(),HttpStatus.ACCEPTED);
		return re;
	}
	
	@PutMapping("/")
	@Transactional
	public ResponseEntity<Void> updateWorkout(@RequestBody Customer c) {
		ResponseEntity<Void>  re;		
		Optional<Customer> customer = customerRepository.findById(c.getCustomerId());
		if(customer.isPresent()) {
			Customer cust = customer.get();
			cust.setEmailId(c.getEmailId());
			cust.setAddress(c.getAddress());
			cust.setMobileNumber(c.getMobileNumber());
		} else {
			throw new CustomerException("Customer with id " +c.getCustomerId()  +" does not exist");
		}
		re = new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		return re;
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCustomer(@PathVariable("id") int id){
		ResponseEntity<Void>  re;
		ResponseEntity<Customer> c = this.findCustomerById(id);
		if(c != null) {
			customerRepository.deleteById(id);
			 re = new ResponseEntity<>(HttpStatus.ACCEPTED);
		} else {
			 re = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			throw new CustomerException("Invalid Id!! Customer with id" +id +"does not exist.");
		}
		
		return re;
	}

	@GetMapping("/{id}")
	public ResponseEntity<Customer> findCustomerById(@PathVariable("id") int id){
		ResponseEntity<Customer> re = null;
		Customer c = null;
		Optional<Customer> customer = customerRepository.findById(id);
		if(customer.isPresent()) {
			c = customer.get();
			re = new ResponseEntity<Customer>(c, HttpStatus.ACCEPTED);
		} else {
			throw new CustomerException("Customer with id " +id +" not found");
		}
		return re;
	}
	
	
	@GetMapping("/customersByName/{fname}&{lname}")
	public ResponseEntity<Customer> findWorkoutByName(@PathVariable("fname") String fname, @PathVariable("lname") String lname){
		Customer customer = customerRepository.findCustomerByFirstNameAndLastName(fname, lname);
		if(customer != null) {
			return new ResponseEntity<Customer>(customer, HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
	}
	
}
