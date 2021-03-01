

package com.capg.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.capg.entities.Customer;
import com.capg.repositories.ICustomerRepository;

public class ICustomerService implements ICustomerRepository{
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("Vehicle");
	EntityManager em = emf.createEntityManager();

	public Customer addCustomer(Customer customer) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(customer);
		tx.commit();
		return customer;
	}

	public Customer removeCustomer(Customer customer) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		Customer fetchCustomer = em.find(Customer.class, viewCustomer(customer).getCustomerId());
		em.remove(fetchCustomer);
		tx.commit();
		return fetchCustomer;
	}

	public Customer updateCustomer(Customer customer) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		Customer fetchCustomer = em.find(Customer.class, viewCustomer(customer).getCustomerId());
		fetchCustomer.setAddress(customer.getAddress());
		fetchCustomer.setEmailId(customer.getEmailId());
		fetchCustomer.setMobileNumber(customer.getMobileNumber());
		tx.commit();
		return fetchCustomer;
	}

	public Customer viewCustomer(Customer customer) {
		TypedQuery<Customer> query = em.createNamedQuery("CustomerByName",Customer.class);
		query.setParameter("firstName", customer.getFirstName());
		query.setParameter("lastName", customer.getLastName());
		return query.getSingleResult();
	}

	public List<Customer> viewAllCustomers(String vtype) {
		TypedQuery<Customer> customers = em.createQuery("select b.customer from  Booking b inner join b.vehicle v"
				+ " where v.type = :vehicleType",Customer.class);
		customers.setParameter("vehicleType", vtype);
		return customers.getResultList();
	}

	public List<Customer> viewAllCustomersByLocation(String location) {
		TypedQuery<Customer> customers = em.createQuery("select b.customer from  Booking b inner join b.vehicle v"
				+ " where v.location = :vehicleLocation",Customer.class);
		customers.setParameter("vehicleLocation", location);
		return customers.getResultList();
	}
	
}