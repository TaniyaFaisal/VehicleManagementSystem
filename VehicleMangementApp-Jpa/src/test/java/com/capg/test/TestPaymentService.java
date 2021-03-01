package com.capg.test;

import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import com.capg.repositories.IUserRepository;
import com.capg.services.IUserService;

public class TestPaymentService {
	IUserRepository userService;
	@Before
	public void setUp() throws Exception {
		userService = new IUserService();
	}

	@Test
	public void testAddPayment() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testCancelPayment() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testViewPayment() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testviewAllPayment() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testCalculateMonthlyRevenue() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testCalculateTotalPayment() {
		fail("Not yet implemented"); // TODO
	}

}
