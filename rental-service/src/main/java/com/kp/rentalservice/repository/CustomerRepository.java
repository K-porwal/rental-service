package com.kp.rentalservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kp.rentalservice.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	List<Customer> findByContactNo(String contactNo);
}
