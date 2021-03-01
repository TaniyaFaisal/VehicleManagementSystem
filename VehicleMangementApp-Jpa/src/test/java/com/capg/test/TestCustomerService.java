package com.capg.test;

import java.util.List;

import org.junit.Before;

import com.capg.entities.Customer;
import com.capg.repositories.ICustomerRepository;
import com.capg.services.ICustomerService;

public class TestCustomerService {
	ICustomerRepository customerService;
	
	@Before
	public void setUp() throws Exception {
		customerService = new ICustomerService();
	}

//	@Test
	public void testAddCustomer() {
		Customer customer = new Customer("Test1","One","test1@gmail.com","9876543210","Bangalore");
		customerService.addCustomer(customer);
		
		customer = new Customer("Test2","Two","test2@gmail.com","1234567890","Bangalore");
		customerService.addCustomer(customer);
	}

//	@Test
	public void testUpdateCustomer() {
		Customer customer = new Customer("Test1","One","updatedEmail@gmail.com","9876543210","Bangalore");
		customerService.updateCustomer(customer);
	}

//	@Test
	public void testViewCustomer() {
		Customer customer = new Customer("Test1","One","test1@gmail.com","9876543210","Bangalore");
		customerService.viewCustomer(customer);
	}

//	@Test
	public void testViewAllCustomers() {
		List<Customer> customers = customerService.viewAllCustomers("Bike");
		System.out.println(customers);
	}

//	@Test
	public void testViewAllCustomersByLocation() {
		List<Customer> customers = customerService.viewAllCustomersByLocation("Delhi");
		System.out.println(customers);
	}
	
//	@Test
	public void testRemoveCustomer() {
		Customer customer = new Customer("rrr","rrr","test1@gmail.com","9876543210","Bangalore");
		customerService.removeCustomer(customer);
	}

}




