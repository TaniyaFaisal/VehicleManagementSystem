package com.example.vehiclemgmt.serviceInterfaces;

import com.example.vehiclemgmt.entities.User;

public interface IUserService {
	public User addUser(User user);
	public void deleteUser(int id);
	public User validateUser(User user);	
	
}
