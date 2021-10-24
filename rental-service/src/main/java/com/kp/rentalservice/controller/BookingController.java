package com.kp.rentalservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kp.rentalservice.model.Booking;
import com.kp.rentalservice.requests.AddBookingRequest;
import com.kp.rentalservice.requests.CancelBookingRequest;
import com.kp.rentalservice.requests.CloseBookingRequest;
import com.kp.rentalservice.service.BookingService;

@RestController
public class BookingController {

	@Autowired
	private BookingService bookingService;
	
	@GetMapping("/bookings")
	public List<Booking> getBookings()
	{
		return bookingService.getAllBookings();
	}
	
	@PostMapping("/bookings/add")
	public String addBooking(@RequestBody AddBookingRequest addBookingRequest)
	{
		return bookingService.addBooking(addBookingRequest);
	}
	
	@PostMapping("/bookings/close")
	public String closeBooking(@RequestBody CloseBookingRequest closeBookingRequest)
	{
		return bookingService.closeBooking(closeBookingRequest);
	}	
	
	@PostMapping("/bookings/cancel")
	public String cancelBooking(@RequestBody CancelBookingRequest cancelBookingRequest)
	{
		return bookingService.cancelBooking(cancelBookingRequest);
	}		
	
}
