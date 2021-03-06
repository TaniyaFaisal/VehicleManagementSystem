package com.project.main.controllers;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
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
	@ResponseStatus(code = HttpStatus.CREATED)
	public Customer addCustomer(@RequestBody Customer c) {
		return customerService.addCustomer(c);
	}
	
	@PutMapping("/customers")
	@ResponseStatus(code = HttpStatus.OK)
	@Transactional
	public void updateCustomer(@RequestBody Customer c) {
		customerService.updateCustomer(c);
	}
	
	@DeleteMapping("/customers/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public void deleteCustomer(@PathVariable("id") int id) {
		customerService.removeCustomer(id);
	}
	
	
	@GetMapping("/customers")
	@ResponseStatus(code = HttpStatus.OK)
	public List<Customer> viewCustomers() {
		return customerService.viewCustomers();
	}
	
	@GetMapping("/customers/{id}")
	public Customer viewCustomerById(@PathVariable("id") int id) {
		return customerService.viewCustomer(id);
	}
	
	@GetMapping("/customerByVehicleType/{type}")
	@ResponseStatus(code = HttpStatus.OK)
	public List<Customer> viewAllCustomers(@PathVariable("type") String type) {
		return customerService.viewAllCustomer(type);
	}
	
	@GetMapping("/customerByVehicleLocation/{loc}")
	@ResponseStatus(code = HttpStatus.OK)
	public List<Customer> viewAllCustomersByLocation(@PathVariable("loc") String loc) {
		return customerService.viewAllCustomersByLocation(loc);
	}
	
}
