package com.kp.rentalservice.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kp.rentalservice.model.Booking;
import com.kp.rentalservice.requests.AddBookingRequest;
import com.kp.rentalservice.requests.CancelBookingRequest;
import com.kp.rentalservice.requests.CloseBookingRequest;
import com.kp.rentalservice.service.utilities.BookingServiceHelper;
import com.kp.rentalservice.service.utilities.MessageUtil;

@Service
public class BookingService {
	
	Logger logger = LoggerFactory.getLogger(BookingService.class);

	@Autowired
	private BookingServiceHelper bookingServiceHelper;
	
	
	public String addBooking(AddBookingRequest addBookingRequest)
	{
		logger.info("Incoming booking request for vehicle id {} by customer with contact no {}",
				addBookingRequest.getVehicleId(),addBookingRequest.getCustomerContactNo());
		if(bookingServiceHelper.checkValidTimeRange(addBookingRequest.getStartTime(),addBookingRequest.getEndTime()))
		{
			if(bookingServiceHelper.checkRegisteredCustomer(addBookingRequest.getCustomerContactNo()))
			{
				if(bookingServiceHelper.checkAvailability(addBookingRequest))
				{
					if(bookingServiceHelper.scheduleBooking(addBookingRequest))
					{
						logger.info(MessageUtil.SUCCESS_ADD_BOOKING);
						return MessageUtil.SUCCESS_ADD_BOOKING;
					}
					else
					{
						logger.info(MessageUtil.FAILURE_ADD_BOOKING);
						return MessageUtil.FAILURE_ADD_BOOKING;
					}
				}
				else
				{
					logger.info(MessageUtil.VEHICLE_UNAVAILABLE);
					return MessageUtil.VEHICLE_UNAVAILABLE;
				}
			}
			else
			{
				logger.info(MessageUtil.UNREGISTERED_CUSTOMER);
				return MessageUtil.UNREGISTERED_CUSTOMER;
			}
		}
		else
		{
			logger.info(MessageUtil.INVALID_TIME_RANGE);
			return MessageUtil.INVALID_TIME_RANGE;
		}
	}
	
	
	public String closeBooking(CloseBookingRequest closeBookingRequest) {
		Integer bookingId = closeBookingRequest.getBookingId();
		Integer branchId = closeBookingRequest.getBranchId();
		String customerContactNo = closeBookingRequest.getCustomerContactNo();
		logger.info("Incoming close booking request by customer with contact no {}", customerContactNo);
		if(bookingServiceHelper.closeBooking(bookingId,branchId,customerContactNo))
		{
			logger.info(MessageUtil.SUCCESS_CLOSE_BOOKING);
			return MessageUtil.SUCCESS_CLOSE_BOOKING;
		}
		else
		{
			logger.info(MessageUtil.FAILURE_CLOSE_BOOKING);
			return MessageUtil.FAILURE_CLOSE_BOOKING;
		}
	}
	
	public String cancelBooking(CancelBookingRequest cancelBookingRequest) {
		Integer bookingId = cancelBookingRequest.getBookingId();
		Integer branchId = cancelBookingRequest.getBranchId();
		String customerContactNo = cancelBookingRequest.getCustomerContactNo();
		logger.info("Incoming cancel booking request for customer with contact no {}",customerContactNo);
		if(bookingServiceHelper.cancelBooking(bookingId,branchId,customerContactNo))
		{
			logger.info(MessageUtil.SUCCESS_CANCEL_BOOKING);
			return MessageUtil.SUCCESS_CANCEL_BOOKING;
		}
		else
		{
			logger.info(MessageUtil.FAILURE_CANCEL_BOOKING);
			return MessageUtil.FAILURE_CANCEL_BOOKING;
		}
	}
	
	public List<Booking> getAllBookings()
	{
		List<Booking> bookings = bookingServiceHelper.getAllBookings();
		for(int i=0;i<bookings.size();i++)
		{
			Booking booking = bookings.get(i);
			logger.info("Booking with id {} belonging to customer with contact number {} and status as {}",
					booking.getId(),booking.getCustomerContactNo(),booking.getStatus());
		}
		return bookings;
	}
}
