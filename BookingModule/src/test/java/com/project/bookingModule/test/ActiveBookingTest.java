package com.project.bookingModule.test;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.project.bookingModule.entities.Booking;
import com.project.bookingModule.services.ActiveBookingService;

@SpringBootTest
class ActiveBookingTest {

	@Autowired
	ActiveBookingService activeBookingService;
	
	@Test
	void testViewActiveBookings() {
		List<Booking> bookings =  activeBookingService.viewActiveBookings();
		System.out.println(bookings);
	}

}
