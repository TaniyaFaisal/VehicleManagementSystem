package com.project.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.main.entities.Driver;

public interface IDriverRepository extends JpaRepository<Driver, Integer>{

	Driver findByFirstNameAndLastName(String firstName, String lastName);

}
