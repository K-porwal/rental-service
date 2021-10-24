package com.kp.rentalservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kp.rentalservice.model.Customer;
import com.kp.rentalservice.requests.AddCustomerRequest;
import com.kp.rentalservice.service.CustomerService;

@RestController
public class CustomerController{
	
	@Autowired
	public CustomerService customerService;

	@PostMapping("/customers/add")
	public String addCustomer(@RequestBody AddCustomerRequest addCustomerRequest)
	{
		return customerService.addCustomer(addCustomerRequest);
	}
	
	@GetMapping("/customers")
	public List<Customer> getAllCustomers()
	{
		return customerService.getAllCustomers();
	}
}
