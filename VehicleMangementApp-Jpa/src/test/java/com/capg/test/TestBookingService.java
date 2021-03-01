package com.capg.test;

import static org.junit.Assert.fail;

import org.junit.Before;

import com.capg.repositories.IBookingRepository;
import com.capg.services.IBookingService;

public class TestBookingService {
	IBookingRepository bookingService;
	@Before
	public void setUp() throws Exception {
		bookingService = new IBookingService();
	}

//	@Test
	public void testAddBooking() {
		fail("Not yet implemented");
	}

//	@Test
	public void testUpdateBooking() {
		fail("Not yet implemented");
	}

//	@Test
	public void testCancelBooking() {
		fail("Not yet implemented");
	}

//	@Test
	public void testViewBooking() {
		fail("Not yet implemented");
	}

//	@Test
	public void testViewAllBooking() {
		fail("Not yet implemented");
	}

//	@Test
	public void testViewAllBookings() {
		fail("Not yet implemented");
	}

//	@Test
	public void testViewAllBookingByDate() {
		fail("Not yet implemented");
	}

}
