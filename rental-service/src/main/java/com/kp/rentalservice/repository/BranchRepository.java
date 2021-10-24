package com.kp.rentalservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kp.rentalservice.model.Branch;

public interface BranchRepository extends JpaRepository<Branch, Integer>{
	
}
