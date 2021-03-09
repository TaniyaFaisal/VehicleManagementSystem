package com.project.main.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.main.entities.Booking;
import com.project.main.entities.Customer;
import com.project.main.entities.User;
import com.project.main.exceptions.AlreadyExistsException;
import com.project.main.exceptions.DeletionException;
import com.project.main.exceptions.NotFoundException;
import com.project.main.exceptions.ValidationException;
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
	public Customer addCustomer(Customer c) throws ValidationException{
		
		final String emailPattern = "^(.+)@(.+)$";
		final String mobilePattern = "(0/91)?[7-9][0-9]{9}";

		String email = c.getEmailId();
		if (email.matches(emailPattern)) {
			
			Customer existingemail = customerRepository.findByEmailId(c.getEmailId());
			if(existingemail != null) {
				throw new AlreadyExistsException("EmailId Already Exists!");
			}
		}
		
		else {
			throw new ValidationException("Invalid Email");
		}
		
		String mobileNo = c.getMobileNumber();
		if (mobileNo.matches(mobilePattern)) {
			
			Customer existingmobileno = customerRepository.findByMobileNumber(c.getMobileNumber());
			if(existingmobileno != null) {
				throw new AlreadyExistsException("Mobile Number Already Exists!");
			}
		}
		
		else {
			throw new ValidationException("Invalid Mobile Number");
		}
		
		Customer customer = customerRepository.findCustomerByFirstNameAndLastName(c.getFirstName(),c.getLastName());		
			if(customer == null){
				customerRepository.save(c);
			}else
				throw new AlreadyExistsException("Customer "+ c.getFirstName()  +" " +c.getLastName() + " already exists.");
			return customer;
	}
	
	@Override
	public Customer removeCustomer(int id){
		Optional<Customer> customer = customerRepository.findById(id);
		if(customer.isEmpty()){
			throw new NotFoundException("Customer with id " +id +" not found");
 		}
		Customer c = customer.get(); 
		List<Booking> booking = bookingRepository.findByCustomer(c);
		if(!(booking.isEmpty())) {
			throw new  DeletionException("Customer with boooking cannot be deleted");
		}
		customerRepository.delete(c);	
		return c;
	}
	
	@Override
	@Transactional
	public Customer updateCustomer(Customer customer) {
		if(customer != null) {
			Customer c = viewCustomer(customer.getCustomerId());
			c.setEmailId(customer.getEmailId());
		return c;	
		}
		else
			throw new NotFoundException("Customer Does Not Exist");
	}
	
	@Override
	public Customer viewCustomer(int id)
	{
		Optional<Customer> customer = customerRepository.findById(id);
		if(customer.isEmpty()){
			throw new NotFoundException("Customer with id " +id +" not found");
 		}
		return customer.get();
	}
	
	@Override
	public List<Customer> viewAllCustomer(String vtype) {
		List<Customer> customers = customerRepository.findByVehicleType(vtype);
		if(customers.isEmpty()){
			throw new NotFoundException("Customer not found");
 		}
		return customers;		
	}
	
	@Override
	public List<Customer> viewAllCustomersByLocation(String location) {
		List<Customer> customers = customerRepository.findByVehicleLocation(location);
		if(customers.isEmpty()){
			throw new NotFoundException("Customer not found");
 		}
		return customers;		
	}
	
	
	public List<Customer> viewCustomers()
	{	
		List<Customer> customers = customerRepository.findAll();
		if(customers.isEmpty()) {
			throw new NotFoundException("No customers found");
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
			throw new AlreadyExistsException("Customer "+ customer.getFirstName()  +" " +customer.getLastName() + " already exists.");
		return customer;
	}
	
}