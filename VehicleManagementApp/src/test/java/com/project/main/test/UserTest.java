package com.project.main.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.project.main.entities.User;
import com.project.main.services.UserService;

@SpringBootTest
class UserTest {
	User user;
	
	@Autowired
	UserService userService;
	
//	@Test
	void testAddUser() {
		User user = new User("testUser","pwd", "Customer");
		User u = userService.addUser(user);
		System.out.print(u);
	}

//	@Test
	void testDeleteUser() {
		 userService.deleteUser(3);
	}

//	@Test
	void testValidateUser() {
		 User user = new User("testUser","pwd", "Customer");
		 User user2 = userService.validateUser(user);
	}

}
