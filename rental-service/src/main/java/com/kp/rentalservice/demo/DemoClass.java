package com.kp.rentalservice.demo;


import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.kp.rentalservice.constants.VehicleType;
import com.kp.rentalservice.model.Branch;
import com.kp.rentalservice.requests.AddBookingRequest;
import com.kp.rentalservice.requests.AddBranchRequest;
import com.kp.rentalservice.requests.AddCustomerRequest;
import com.kp.rentalservice.requests.AddVehicleRequest;
import com.kp.rentalservice.requests.CloseBookingRequest;
import com.kp.rentalservice.responses.VehiclesByBranchIdResponse;
import com.kp.rentalservice.service.BookingService;
import com.kp.rentalservice.service.BranchService;
import com.kp.rentalservice.service.CustomerService;


@Component
public class DemoClass {
	
	Logger logger = LoggerFactory.getLogger(DemoClass.class);
	
	@Autowired
	private BranchService branchService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private BookingService bookingService;
	
	
	public Integer demoBranch(AddBranchRequest addBranchRequest)
	{

		branchService.addBranch(addBranchRequest);
		
		List<Branch> branches =  branchService.getBranches();
		logger.info("Total branches available {} " ,branches.size());
		
		return branches.get(0).getId();
		
	}
	
	public UUID demoGetCarVehicleIdToBook()
	{
		
		return branchService.getBranches().get(0).getCars().get(0).getId();
	}
	
	public Integer demoRegisterCustomer(AddCustomerRequest addCustomerRequest) {
		customerService.addCustomer(addCustomerRequest);
		return customerService.getAllCustomers().get(0).getId();
	}
	
	public void demoViewAllCustomers()
	{
		customerService.getAllCustomers();
	}
	
	public void demoScheduleBooking(AddBookingRequest addBookingRequest)
	{
		bookingService.addBooking(addBookingRequest);
	}
	
	public void demoCloseBooking(String customerContactNo, Integer branchId)
	{
		Integer bookingId = bookingService.getAllBookings().get(0).getId();
		CloseBookingRequest closeBookingRequest = new CloseBookingRequest(customerContactNo,
				branchId,bookingId);
		bookingService.closeBooking(closeBookingRequest);
	}

	public void demoViewAllBookings() {
		bookingService.getAllBookings();
	}
	
	public void demoAddVehicle(AddVehicleRequest addVehicleRequest) {
		branchService.addVehicle(addVehicleRequest);
	}
	
	public void demoViewVehiclesByBranchId(Integer branchId) 
	{
		VehiclesByBranchIdResponse response= branchService.getVehicles(branchId);
	}
	
	
	@EventListener(ApplicationReadyEvent.class)
	public void demoRentalService() {

		String branchName = "Demo";
		logger.info("Adding a {} branch",branchName);
		AddBranchRequest addBranchRequest = new AddBranchRequest(branchName);
		Integer branchId = demoBranch(addBranchRequest);
		
		
	    String idProofNo = "1234";
	    String name = "Kshitij";
	    String customerContactNo = "7007892234";
	    String address = "Nadesar, Varanasi";
		AddCustomerRequest addCustomerRequest = new AddCustomerRequest(idProofNo,name,customerContactNo,address);
		Integer customerId = demoRegisterCustomer(addCustomerRequest);
		demoViewAllCustomers();
		logger.info("Customer with name {} and contact no {} created",name,customerContactNo);
		
		UUID vehicleId = demoGetCarVehicleIdToBook();
		AddBookingRequest addBookingRequest = new AddBookingRequest(branchId,customerContactNo,vehicleId,"12:00","13:00");
		demoScheduleBooking(addBookingRequest);
		demoViewAllBookings();
		
		demoCloseBooking(customerContactNo, branchId);
		demoViewAllBookings();
		
	    VehicleType vehicleType = VehicleType.BIKE;
	    String registrationNo = "UP 65 AB 6677";
		String companyName =  "TVS";
		String colour = "RED";
		Integer hourlyRentalPrice =  150;
		AddVehicleRequest addVehicleRequest = new AddVehicleRequest(registrationNo,companyName,
				colour,hourlyRentalPrice,vehicleType,branchId);
		demoAddVehicle(addVehicleRequest);
		
		demoViewVehiclesByBranchId(branchId);
	}
	
}
