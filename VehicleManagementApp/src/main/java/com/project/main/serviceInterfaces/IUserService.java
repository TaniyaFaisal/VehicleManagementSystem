package com.project.main.serviceInterfaces;

import com.project.main.entities.User;

public interface IUserService {
	public User addUser(User user);
	public void deleteUser(int id);
	public User validateUser(User user);	
	
}
