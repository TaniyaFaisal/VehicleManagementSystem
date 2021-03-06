package com.project.main.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.project.main.entities.Booking;
import com.project.main.entities.Customer;
import com.project.main.entities.Driver;
import com.project.main.entities.Vehicle;
import com.project.main.services.BookingService;

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
		customer = new Customer("Test","one","test@gmail.com", "9876543210", "#23,Bangalore");
		driver = new Driver("Raj","Sharma","#45,Bangalore","12345672","raj@gmail.com","FG2303");
		vehicle = new Vehicle("KA05828", driver, "Car", "SUV", "Bangalore", "---", 4, 35.00, 1000);
		booking = new Booking(customer, vehicle, LocalDate.of(2021, 03, 05), LocalDate.of(2021, 03, 06), "----", 5000, 200);
	}
	
//	@Test
	void testAddBooking() {
		Booking b= bookingService.addBooking(booking);
		System.out.println(b);
	}

//	@Test
	void testViewAllBookings() {
		List<Booking> bookings = bookingService.viewAllBookings();
		System.out.println(bookings);
		//assertEquals(3, bookings.size());
	}

//	@Test
	void testDeleteBooking() {
		bookingService.cancelBooking(39);
	}

//	@Test
	void testViewBooking() {
		Booking b = bookingService.viewBooking(5);
		System.out.println(b);
	}

//	@Test
	void testUpdateBooking() {
		booking = new Booking(9, customer, vehicle, LocalDate.of(2021, 03, 05), LocalDate.of(2021, 03, 06), "xxxx", 5000, 200);
		Booking b = bookingService.updateBooking(booking);
		assertEquals("xxxx", b.getBookingDescription());
		System.out.println(b);
	}

//	@Test
	void testViewBookingByCustomer() {
		List<Booking> bookings = bookingService.viewAllBooking(customer);
		System.out.println(bookings);
	}

//	@Test
	void testViewBookingByVehicle() {
		List<Booking> bookings = bookingService.viewAllBookingByVehicle(vehicle);
		System.out.println(bookings);
	}

//	@Test
	void testViewBookingByBookingDate() {
		List<Booking> bookings = bookingService.viewAllBookingByDate(LocalDate.of(2021, 03, 04));
		System.out.println(bookings);
	}

}
