package com.capg.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.capg.entities.Driver;
import com.capg.entities.Vehicle;
import com.capg.repositories.IVehicleRepository;

public class IVehicleService implements IVehicleRepository{
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("Vehicle");
	EntityManager em = emf.createEntityManager();

	public Vehicle addVehicle(Vehicle v) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(v);
		tx.commit();
		return v;
	}

	public Vehicle updateVehicle(Vehicle v) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		Vehicle fetchVehicle = em.find(Vehicle.class, viewVehicle(v).getVehicleId());
		fetchVehicle.setLocation(v.getLocation());
		fetchVehicle.setChargesPerKm(v.getChargesPerKm());
		fetchVehicle.setFixedCharges(v.getFixedCharges());
		//similarly for other fields
		tx.commit();
		return fetchVehicle;
	}

	public Vehicle cancelVehicle(Vehicle v) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		Vehicle fetchVehicle = em.find(Vehicle.class, viewVehicle(v).getVehicleId());
		em.remove(fetchVehicle);
		tx.commit();
		return fetchVehicle;
	}

	public Vehicle viewVehicle(Vehicle v) {
		TypedQuery<Vehicle> query = em.createNamedQuery("select v from vehicle v where v.vehicleNumber = :vehicleNumber",Vehicle.class);
		query.setParameter("firstName", v.getVehicleNumber());
		return query.getSingleResult();
	}

	public List<Vehicle> viewAllVehicle(Driver driver) {
		TypedQuery<Vehicle> query = em.createNamedQuery("select v from vehicle v inner join Driver d where d.firstName = :firstName",Vehicle.class);
		query.setParameter("firstName", driver.getFirstName());
		return query.getResultList();

	}
	
	//add more methods if required

}
