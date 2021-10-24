package com.kp.rentalservice.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kp.rentalservice.model.Branch;
import com.kp.rentalservice.requests.AddBranchRequest;
import com.kp.rentalservice.requests.AddVehicleRequest;
import com.kp.rentalservice.responses.VehiclesByBranchIdResponse;
import com.kp.rentalservice.service.BranchService;

@RestController
public class BranchController {

	@Autowired
	private BranchService branchService;
	
	@PostMapping("/branches/addBranch")
	public String addBranch(@RequestBody @Valid AddBranchRequest addBranchRequest)
	{
		return branchService.addBranch(addBranchRequest);
	}
	
	@GetMapping("/branches")
	public List<Branch> getAllBranches()
	{
		return branchService.getBranches();
	}
	
	@PostMapping("/branches/addVehicle")
	public String addVehicle(@RequestBody AddVehicleRequest addVehicleRequest) 
	{
		return branchService.addVehicle(addVehicleRequest);
	}
	
	@GetMapping("/branches/{id}")
	public VehiclesByBranchIdResponse getVehicles(@PathVariable Integer id) 
	{
		 return branchService.getVehicles(id);
	}	
}
