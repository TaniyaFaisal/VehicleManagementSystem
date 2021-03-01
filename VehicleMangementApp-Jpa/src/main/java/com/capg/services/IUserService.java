package com.capg.services;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.capg.entities.User;
import com.capg.repositories.IUserRepository;

public class IUserService implements IUserRepository{
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("Vehicle");
	EntityManager em = emf.createEntityManager();
	
	public User addUser(User user) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(user);
		tx.commit();
		return user;
	}

	public User validateUser(User user) {
		TypedQuery<User> query = em.createQuery("select u from User u where u.userId=:userId and u.password=:password", User.class);
		query.setParameter("userId", user.getUserId());
		query.setParameter("password", user.getPassword());
		return query.getSingleResult();
	}

	public User removeUser(User user) {
		em.getTransaction().begin();
		em.remove(user);
		em.getTransaction().commit();
		return user;
	}

	public User signOut(User user) {
		// TODO Auto-generated method stub
		return null;
	}
}
