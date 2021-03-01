package com.capg.test;

import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import com.capg.repositories.IUserRepository;
import com.capg.services.IUserService;

public class TestUserService {
	IUserRepository userService;
	@Before
	public void setUp() throws Exception {
		userService = new IUserService();
	}

	@Test
	public void testAddUser() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testValidateUser() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testRemoveUser() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testSignOut() {
		fail("Not yet implemented"); // TODO
	}

}
