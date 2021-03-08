package com.project.main.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.main.entities.User;
import com.project.main.exceptions.UserException;
import com.project.main.exceptions.UserNotFoundException;
import com.project.main.repositories.IUserRepository;
import com.project.main.serviceInterfaces.IUserService;

@Service
public class UserService implements IUserService{

	@Autowired
	IUserRepository userRepository;

	@Override
	public User addUser(User user) {
		User u = userRepository.findByUserName(user.getUserName());
		if(u == null) {
			userRepository.save(user);
		}else {
			throw new UserException("User " +user.getUserId()+" already exixts");
		}
		return user;
	}

	@Override
	public void deleteUser(int id) {
		Optional<User> user = userRepository.findById(id);
		if(user.isEmpty()){
			throw new UserNotFoundException("User not found with id : "+id);
		}
		User u = user.get();
		userRepository.delete(u);

	}

	@Override
	public User validateUser(User user) {
			User u = userRepository.findUserByUserNameAndPassword(user.getUserName(), user.getPassword());
			if(u == null) 
			{
				throw new UserException("User name : "+user.getUserName()+" or the User password : "+user.getPassword()+" is invalid");
			}
		return user;
	}

}
