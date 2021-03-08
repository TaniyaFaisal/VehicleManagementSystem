package com.project.main.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.main.entities.Booking;
import com.project.main.entities.Customer;
import com.project.main.entities.User;
import com.project.main.exceptions.CustomerException;
import com.project.main.exceptions.CustomerNotFoundException;
import com.project.main.repositories.IBookingRepository;
import com.project.main.repositories.ICustomerRepository;
import com.project.main.repositories.IUserRepository;
import com.project.main.serviceInterfaces.ICustomerService;

@Service
public class CustomerService implements ICustomerService{
	@Autowired
	ICustomerRepository customerRepository;
	
	@Autowired
	IBookingRepository bookingRepository;
	
	@Autowired 
	IUserRepository userRepository;
	
	@Override
	public Customer addCustomer(Customer c){
		Customer customer = customerRepository.findCustomerByFirstNameAndLastName(c.getFirstName(),c.getLastName());		
			if(customer == null){
				customerRepository.save(c);
			}else
				throw new CustomerException("Customer "+ c.getFirstName()  +" " +c.getLastName() + " already exists.");
			return customer;
	}
	
	@Override
	public Customer removeCustomer(int id){
		Optional<Customer> customer = customerRepository.findById(id);
		if(customer.isEmpty()){
			throw new CustomerNotFoundException("Customer with id " +id +" not found");
 		}
		Customer c = customer.get(); 
		List<Booking> booking = bookingRepository.findByCustomer(c);
		if(!(booking.isEmpty())) {
			throw new CustomerException("Customer with boooking cannot be deleted");
		}
		customerRepository.delete(c);	
		return c;
	}
	
	@Override
	@Transactional
	public Customer updateCustomer(Customer customer) {
		Customer c = viewCustomer(customer.getCustomerId());
		c.setEmailId(customer.getEmailId());
		return c;	
	}
	
	@Override
	public Customer viewCustomer(int id)
	{
		Optional<Customer> customer = customerRepository.findById(id);
		if(customer.isEmpty()){
			throw new CustomerNotFoundException("Customer with id " +id +" not found");
 		}
		return customer.get();
	}
	
	@Override
	public List<Customer> viewAllCustomer(String vtype) {
		List<Customer> customers = customerRepository.findByVehicleType(vtype);
		if(customers.isEmpty()){
			throw new CustomerNotFoundException("Customer not found");
 		}
		return customers;		
	}
	
	@Override
	public List<Customer> viewAllCustomersByLocation(String location) {
		List<Customer> customers = customerRepository.findByVehicleLocation(location);
		if(customers.isEmpty()){
			throw new CustomerNotFoundException("Customer not found");
 		}
		return customers;		
	}
	
	
	public List<Customer> viewCustomers()
	{	
		List<Customer> customers = customerRepository.findAll();
		if(customers.isEmpty()) {
			throw new CustomerNotFoundException("No customers found");
		}
		return customers;
	}
	
	public Customer addCustomer(String password,Customer customer) {
		Customer cust = customerRepository.findCustomerByFirstNameAndLastName(customer.getFirstName(),customer.getLastName());		
		if(cust == null){
			User user = new User();
			user.setRole("customer");
			user.setUserName(customer.getFirstName());
			user.setPassword(password);
			customerRepository.save(customer);
			userRepository.save(user);
		}
		else
			throw new CustomerException("Customer "+ customer.getFirstName()  +" " +customer.getLastName() + " already exists.");
		return customer;
	}
	
}
