package com.kp.rentalservice.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kp.rentalservice.model.Customer;
import com.kp.rentalservice.repository.CustomerRepository;
import com.kp.rentalservice.requests.AddCustomerRequest;
import com.kp.rentalservice.service.utilities.MessageUtil;

@Service
public class CustomerService {
	
	Logger logger = LoggerFactory.getLogger(CustomerService.class);

	@Autowired
	private CustomerRepository customerRepository;
	
	public String addCustomer(AddCustomerRequest addCustomerRequest) {
		String idProofNo = addCustomerRequest.getIdProofNo();
		String name = addCustomerRequest.getName();
		String address= addCustomerRequest.getAddress();
		String contactNo = addCustomerRequest.getContactNo();
		
		Customer customer = new Customer(idProofNo,name,address,contactNo);
		Customer customerCreated = customerRepository.save(customer);
		if(!customerCreated.getId().toString().isEmpty())
		{
			logger.info(MessageUtil.CUSTOMER_REGISTRATION_SUCCESS);
			return MessageUtil.CUSTOMER_REGISTRATION_SUCCESS;
		}
		else			
		{	
			logger.info(MessageUtil.CUSTOMER_REGISTRATION_FAILURE);
			return MessageUtil.CUSTOMER_REGISTRATION_FAILURE;
		}
	}
	
	public List<Customer> getAllCustomers()
	{
		return customerRepository.findAll();
	}
	
}
