package com.kp.rentalservice.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kp.rentalservice.constants.BikeType;
import com.kp.rentalservice.constants.CarType;
import com.kp.rentalservice.constants.VehicleType;
import com.kp.rentalservice.model.Bike;
import com.kp.rentalservice.model.Branch;
import com.kp.rentalservice.model.Car;
import com.kp.rentalservice.repository.BranchRepository;
import com.kp.rentalservice.requests.AddBranchRequest;
import com.kp.rentalservice.requests.AddVehicleRequest;
import com.kp.rentalservice.responses.VehiclesByBranchIdResponse;
import com.kp.rentalservice.service.utilities.BranchServiceHelper;
import com.kp.rentalservice.service.utilities.MessageUtil;

@Service
public class BranchService {

	Logger logger = LoggerFactory.getLogger(BranchService.class);
	
	@Autowired
	private BranchRepository branchRepository;
	
	@Autowired
	private BranchServiceHelper branchServiceHelper;
	
	public String addBranch(AddBranchRequest addBranchRequest)
	{
		Branch newBranch = new Branch(addBranchRequest.getName());
		branchRepository.save(newBranch);
		logger.info(MessageUtil.SUCCESS_ADD_BRANCH);
		return MessageUtil.SUCCESS_ADD_BRANCH;
	}
	
	public List<Branch> getBranches()
	{
		return branchRepository.findAll();
	}
	
	public String addVehicle(AddVehicleRequest addVehicleRequest)
	{
		logger.info("Incoming add vehicle request for vehicle with registration no {} ",
				addVehicleRequest.getRegistrationNo());
		Integer branchId = addVehicleRequest.getBranchId();
		Optional<Branch> requiredBranch = branchRepository.findById(branchId);
		if(requiredBranch.isPresent())
		{
			Branch foundBranch = requiredBranch.get();
			
			String registrationNo = addVehicleRequest.getRegistrationNo();
			
			String companyName = addVehicleRequest.getCompanyName();
			
			String colour = addVehicleRequest.getColour();
			
			Integer hourlyRentalPrice = addVehicleRequest.getHourlyRentalPrice();
			
			UUID vehicleId = UUID.randomUUID();
			
			if(addVehicleRequest.getVehicleType()==VehicleType.CAR)
			{
				Car car = new Car(vehicleId,registrationNo,companyName,colour,hourlyRentalPrice,"PETROL",CarType.CAP_6);
				List<Car> cars = foundBranch.getCars();
				cars.add(car);
				foundBranch.setCars(cars);
				branchRepository.save(foundBranch);
				logger.info("Added a new car of company {} with vehicle id {}",companyName,vehicleId);
				return MessageUtil.SUCCESS_ADD_CAR;
			}
			
			else if(addVehicleRequest.getVehicleType()==VehicleType.BIKE)
			{
				Bike bike = new Bike(vehicleId,registrationNo,companyName,colour,hourlyRentalPrice,BikeType.CC_100);
				List<Bike> bikes = foundBranch.getBikes();
				bikes.add(bike);
				foundBranch.setBikes(bikes);
				branchRepository.save(foundBranch);
				logger.info("Added a new bike of company {} with vehicle id {}",companyName,vehicleId);
				return MessageUtil.SUCCESS_ADD_BIKE;
			}
			logger.info(MessageUtil.INVALID_VEHICLE_DETAILS);
			return MessageUtil.INVALID_VEHICLE_DETAILS;
		}
		else
		{
			logger.info(MessageUtil.INVALID_BRANCH_ID);
			return MessageUtil.INVALID_BRANCH_ID;
		}
	}
	
	public VehiclesByBranchIdResponse getVehicles(Integer branchId)
	{
		 return branchServiceHelper.getVehicles(branchId);
	}
}
