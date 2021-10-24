package com.kp.rentalservice.service.utilities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kp.rentalservice.constants.BookingType;
import com.kp.rentalservice.model.Bike;
import com.kp.rentalservice.model.Booking;
import com.kp.rentalservice.model.Branch;
import com.kp.rentalservice.model.Car;
import com.kp.rentalservice.model.utilities.BikeWithBookings;
import com.kp.rentalservice.model.utilities.CarWithBookings;
import com.kp.rentalservice.model.utilities.TimeRange;
import com.kp.rentalservice.repository.BookingRepository;
import com.kp.rentalservice.repository.BranchRepository;
import com.kp.rentalservice.responses.VehiclesByBranchIdResponse;
import com.kp.rentalservice.service.BookingService;

@Component
public class BranchServiceHelper {

	Logger logger = LoggerFactory.getLogger(BookingService.class);
	
	@Autowired
	private BranchRepository branchRepository;
	
	@Autowired
	private BookingRepository bookingRepository;

	public VehiclesByBranchIdResponse getVehicles(Integer branchId)
	{
		VehiclesByBranchIdResponse response = new VehiclesByBranchIdResponse();
		Optional<Branch> branch = branchRepository.findById(branchId);
		if(branch.isPresent())
		{
			Branch foundBranch = branch.get();
			List<Bike> bikes = foundBranch.getBikes();
			List<Car> cars = foundBranch.getCars();
			
			Collections.sort(cars);
			Collections.sort(bikes);
			
			List<Booking> bookings = bookingRepository.findByStatus(BookingType.ACTIVE);
			List<CarWithBookings> carsWithbookings = new ArrayList<>();
			List<BikeWithBookings> bikesWithbookings = new ArrayList<>();
			response.setBranchId(branchId);
			
			logger.info("Cars sorted on their hourly rental price are -");
			for(int i=0;i<cars.size();i++)
			{
				List<TimeRange> bookingsForCar =  new ArrayList<>();
				CarWithBookings carWithBookings = new CarWithBookings(cars.get(i),bookingsForCar);
				Car car = cars.get(i);
				logger.info("Car with vehicle id {} and company name {} and hourly rental price {}",
						car.getId(),car.getCompanyName(),car.getHourlyRentalPrice());
				for(int j=0;j<bookings.size();j++)
				{
					UUID vehicleId = bookings.get(j).getVehicleId();
					if(car.getId().compareTo(vehicleId) == 0)
					{
						TimeRange timeRange = new TimeRange(bookings.get(j).getStartTime(), bookings.get(j).getEndTime());	
						logger.info("Booking for car with vehicleId {}",vehicleId);
						logger.info("ACTIVE booking time slot {},{}",timeRange.getStartTime(),timeRange.getEndTime());
						bookingsForCar.add(timeRange);
					}
				}
				
				carsWithbookings.add(carWithBookings);
			}
			response.setCarsWithBookings(carsWithbookings);

			logger.info("--------------------------------------------------------------");
			logger.info("Bikes sorted on their hourly rental price are -");
			for(int i=0;i<bikes.size();i++)
			{
				List<TimeRange> bookingsForBike =  new ArrayList<>();
				BikeWithBookings bikeWithBookings = new BikeWithBookings(bikes.get(i),bookingsForBike); 
				Bike bike = bikes.get(i);
				
				logger.info("Bike with vehicle id {} and company name {} and hourly rental price {}",
						bike.getId(),bike.getCompanyName(),bike.getHourlyRentalPrice());
				for(int j=0;j<bookings.size();j++)
				{
					UUID vehicleId = bookings.get(j).getVehicleId();
					if(bike.getId().compareTo(vehicleId) == 0)
					{
						TimeRange timeRange = new TimeRange(bookings.get(j).getStartTime(), bookings.get(j).getEndTime());
						logger.info("Booking for bike with vehicleId {}",vehicleId);
						logger.info("ACTIVE booking time slot {}, {}",timeRange.getStartTime(),timeRange.getEndTime());
						bookingsForBike.add(timeRange);
					}
				}
				bikesWithbookings.add(bikeWithBookings);
			}
			
			response.setBikesWithbookings(bikesWithbookings);
		}
		return response;
	}
}
