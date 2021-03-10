package com.project.vm.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.project.vm.entities.Booking;
import com.project.vm.entities.Customer;
import com.project.vm.entities.Driver;
import com.project.vm.entities.Vehicle;
import com.project.vm.exceptions.DeletionException;
import com.project.vm.exceptions.NotFoundException;
import com.project.vm.services.BookingService;

@SpringBootTest
class BookingTest {
	
	Customer customer;
	Driver driver;
	Vehicle vehicle;
	Booking booking;
	
	@Autowired
	BookingService bookingService;
	
	@BeforeEach
	void setUp() {
		customer = new Customer();
		vehicle = new Vehicle();
		booking = new Booking();
	}
	
//	@Test
	void testAddBooking() {
		customer.setFirstName("Test");
		customer.setLastName("Cust");
		vehicle.setVehicleNumber("AF786023");
		booking = new Booking(customer, vehicle,LocalDate.of(2021, 03, 9), LocalDate.of(2021, 03, 10), "Booking for one day", 200);
		Booking b= bookingService.addBooking(booking);
		System.out.println(b);
	}

//	@Test
	void testViewAllBookings() {
		List<Booking> bookings = bookingService.viewAllBookings();
		System.out.println(bookings);
		assertEquals(5, bookings.size());
	}

//	@Test
	void testDeleteBooking() {
		int id = 8;
		bookingService.cancelBooking(id);
	}
	
//	@Test
	void testDeleteInvalidBooking() {
		int id = 0;
		Exception exception = assertThrows(NotFoundException.class, () -> {
			bookingService.cancelBooking(id);;
	    });
	    String expectedMessage = "not found";
	    String actualMessage = exception.getMessage();
	    assertTrue(actualMessage.contains(expectedMessage));
	}
	
//	@Test
	void testDeleteBookingWithPayment() {
		int id = 3;
		Exception exception = assertThrows(DeletionException.class, () -> {
			bookingService.cancelBooking(id);;
	    });
	    String expectedMessage = "cannot be deleted";
	    String actualMessage = exception.getMessage();
	    assertTrue(actualMessage.contains(expectedMessage));
	}

//	@Test
	void testViewBooking() {
		Booking b = bookingService.viewBooking(3);
		System.out.println(b);
	}

//	@Test
	void testUpdateBooking() {
		customer.setFirstName("Test");
		customer.setLastName("Cust");
		vehicle.setVehicleNumber("AF786023");
		booking = new Booking(customer, vehicle,LocalDate.of(2021, 03, 9), LocalDate.of(2021, 03, 11), "Booking for two days", 200);	
		Booking b = bookingService.updateBooking(booking);
		assertEquals("Booking for two days", b.getBookingDescription());
		assertEquals(LocalDate.of(2021, 03, 11), b.getBookedTillDate());
		System.out.println(b);
	}

//	@Test
	void testViewBookingByCustomer() {
		List<Booking> bookings = bookingService.viewAllBooking("Priya");
		System.out.println(bookings);
	}

//	@Test
	void testViewBookingByVehicle() {
		List<Booking> bookings = bookingService.viewAllBookingByVehicle("AF786023");
		System.out.println(bookings);
	}

//	@Test
	void testViewBookingByBookingDate() {
		List<Booking> bookings = bookingService.viewAllBookingByDate(LocalDate.of(2021, 03, 9));
		System.out.println(bookings);
	}

}
