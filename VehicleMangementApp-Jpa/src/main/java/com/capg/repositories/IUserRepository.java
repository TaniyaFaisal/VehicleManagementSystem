package com.capg.repositories;

import com.capg.entities.User;

public interface IUserRepository {
	public User addUser(User user);
	public User validateUser(User user);
	public User removeUser(User user);
	public User signOut(User user);
}
