package com.project.main.controllers;

import java.util.List;

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

import com.project.main.entities.Customer;
import com.project.main.repositories.ICustomerRepository;
import com.project.main.services.CustomerService;

@RestController
@RequestMapping(path = "/api/v1")
public class CustomerController {
	@Autowired
	ICustomerRepository customerRepository;
	
	@Autowired
	CustomerService customerService;
	
	
	@PostMapping("/customers")
	public ResponseEntity<Customer> addCustomer(@RequestBody Customer c) {
		Customer customer = customerService.addCustomer(c);
		return new ResponseEntity<>(customer,HttpStatus.CREATED);
	}
	
	@PutMapping("/customers")
	@Transactional
	public ResponseEntity<String> updateCustomer(@RequestBody Customer c) {
		customerService.updateCustomer(c);
		return new ResponseEntity<>("Successfuly updated", HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/customers/{id}")
	public  ResponseEntity<String> removeCustomer(@PathVariable("id") int id) {
		customerService.removeCustomer(id);
		return new ResponseEntity<>("Successfuly deleted", HttpStatus.OK);
	}
	
	
	@GetMapping("/customers")
	public ResponseEntity<List<Customer>> viewCustomers() {
		List<Customer> customer = customerService.viewCustomers();
		return new ResponseEntity<>(customer,HttpStatus.OK);
	}
	
	@GetMapping("/customers/{id}")
	public Customer viewCustomerById(@PathVariable("id") int id) {
		return customerService.viewCustomer(id);
	}
	
	@GetMapping("/customerByVehicleType/{type}")
	public ResponseEntity<List<Customer>> viewAllCustomers(@PathVariable("type") String type) {
		List <Customer> customer = customerService.viewAllCustomer(type);
		return new ResponseEntity<>(customer,HttpStatus.OK);
	}
	
	@GetMapping("/customerByVehicleLocation/{loc}")
	public ResponseEntity<List<Customer>> viewAllCustomersByLocation(@PathVariable("loc") String loc) {
		List <Customer> customer = customerService.viewAllCustomersByLocation(loc);
		return new ResponseEntity<>(customer,HttpStatus.OK);
	}
	
	
}