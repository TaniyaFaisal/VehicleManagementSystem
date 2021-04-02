package com.example.vehiclemgmt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.vehiclemgmt.entities.User;

@Repository
public interface IUserRepository extends JpaRepository<User, Integer>{

	public User findByUserName(String name);
	public User findUserByUserNameAndPassword(String userName , String password);
}
