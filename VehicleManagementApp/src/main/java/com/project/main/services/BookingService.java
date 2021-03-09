package com.project.main.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.main.entities.Booking;
import com.project.main.entities.Customer;
import com.project.main.entities.Payment;
import com.project.main.entities.Vehicle;
import com.project.main.exceptions.DeletionException;
import com.project.main.exceptions.NotFoundException;
import com.project.main.exceptions.ValidationException;
import com.project.main.repositories.IBookingRepository;
import com.project.main.repositories.ICustomerRepository;
import com.project.main.repositories.IPaymentRepository;
import com.project.main.repositories.IVehicleRepository;
import com.project.main.serviceInterfaces.IBookingService;

@Service
public class BookingService implements IBookingService{
	@Autowired
	IBookingRepository bookingRepository;

	@Autowired
	IPaymentRepository paymentRepository;

	@Autowired
	ICustomerRepository customerRepository;

	@Autowired
	IVehicleRepository vehicleRepository;

	Optional<Booking> booking = null;

	@Override
	@Transactional
	public Booking addBooking(Booking b) throws ValidationException{
		LocalDate currentDate = LocalDate.now();
		if(b.getBookedTillDate().isBefore(currentDate) || b.getBookingDate().isBefore(currentDate)) {
			throw new ValidationException("Booking dates cannot be before current date");
		}
		try {	
			Vehicle vehicle = vehicleRepository.findVehicleByVehicleNumber(b.getVehicle().getVehicleNumber());
			Customer customer = customerRepository.findCustomerByFirstNameAndLastName(b.getCustomer().getFirstName(),b.getCustomer().getLastName());
			if(vehicle != null) {
				b.setVehicle(vehicle);
			}
			if(customer != null ) {
				b.setCustomer(customer);
			}
		}catch(NotFoundException e) {
			e.getMessage();	
		}		
		bookingRepository.save(b);
		return b;
	}

	@Override
	public Booking cancelBooking(int id) {
		Booking b1 = this.viewBooking(id);
		Payment payment = paymentRepository.findPaymentsByBooking(b1);
		if(payment != null) {
			throw new DeletionException("Booking with payment cannot be deleted");
		}
		bookingRepository.delete(b1);
		return b1;
	}

	@Override
	@Transactional
	public Booking updateBooking(Booking b) {
		Booking booking1 = this.viewBooking(b.getBookingId());
		booking1.setBookedTillDate(b.getBookedTillDate());
		booking1.setBookingDescription(b.getBookingDescription());
		return booking1;
	}

	@Override
	public List<Booking> viewAllBooking(Customer customer) {
		Customer c = customerRepository.findCustomerByFirstName(customer.getFirstName());
		List<Booking> bookings = bookingRepository.findByCustomer(c);
		if(bookings.isEmpty()) {
			throw new NotFoundException("No bookings found for customer : " +customer.getFirstName());
		}
		return bookings;

	}

	@Override
	public List<Booking> viewAllBookingByVehicle(Vehicle v) {
		Vehicle vehicle = vehicleRepository.findVehicleByVehicleNumber(v.getVehicleNumber());
		List<Booking> bookings = bookingRepository.findByVehicle(vehicle);
		if(bookings.isEmpty()) {
			throw new NotFoundException("No bookings found for vehicle :" +v.getVehicleNumber());
		}
		return bookings;
	}

	@Override
	public List<Booking> viewAllBookingByDate(LocalDate bDate) {
		List<Booking> bookings = bookingRepository.findByBookingDate(bDate);
		if(bookings.isEmpty()) {
			throw new NotFoundException("No bookings found for date : " +bDate);
		}
		return bookings;
	}

	@Override
	public Booking viewBooking(int id) {
		booking = bookingRepository.findById(id);
		if(booking.isEmpty()) {
			throw new NotFoundException("Booking with id " +id +" not found.");
		}
		Booking b1 = booking.get();
		return b1;
	}


	public List<Booking> viewAllBookings() {
		List<Booking> bookings = bookingRepository.findAll();
		if(bookings.isEmpty()) {
			throw new NotFoundException("No bookings found");
		}
		return bookings;
	}

}
