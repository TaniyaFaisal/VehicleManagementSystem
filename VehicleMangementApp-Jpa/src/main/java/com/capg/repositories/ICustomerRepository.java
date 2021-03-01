package com.capg.repositories;

import java.util.List;

import com.capg.entities.Customer;

public interface ICustomerRepository {
	public Customer addCustomer(Customer customer);
	public Customer removeCustomer(Customer customer);
	public Customer updateCustomer(Customer customer);
	public Customer viewCustomer(Customer customer);
	public List<Customer> viewAllCustomers(String vtype);
	public List<Customer> viewAllCustomersByLocation(String location);
	

}
