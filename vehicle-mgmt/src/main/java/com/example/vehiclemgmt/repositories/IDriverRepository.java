package com.example.vehiclemgmt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.vehiclemgmt.entities.Driver;



public interface IDriverRepository extends JpaRepository<Driver, Integer>{

	Driver findByFirstNameAndLastName(String firstName, String lastName);

}
