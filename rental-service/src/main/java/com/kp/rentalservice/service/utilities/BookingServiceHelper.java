package com.kp.rentalservice.service.utilities;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
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
import com.kp.rentalservice.model.Vehicle;
import com.kp.rentalservice.repository.BikeRepository;
import com.kp.rentalservice.repository.BookingRepository;
import com.kp.rentalservice.repository.BranchRepository;
import com.kp.rentalservice.repository.CarRepository;
import com.kp.rentalservice.repository.CustomerRepository;
import com.kp.rentalservice.requests.AddBookingRequest;
import com.kp.rentalservice.service.BookingService;

@Component
public class BookingServiceHelper {
	
	Logger logger = LoggerFactory.getLogger(BookingServiceHelper.class);
	
	@Autowired
	private BookingRepository bookingRepository;
	
	@Autowired
	private CarRepository carRepository;
	
	@Autowired
	private BikeRepository bikeRepository;
	
	@Autowired
	private BranchRepository branchRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	public Boolean checkValidTimeRange(String givenStartTime,String givenEndTime) {
		LocalTime startT = LocalTime.parse(givenStartTime);
		LocalTime endT = LocalTime.parse(givenEndTime);
		
		long minutesBetween = ChronoUnit.MINUTES.between(startT,endT);
		
		if(startT.isBefore(endT) && minutesBetween%60==0)
			return true;
		else
			return false;
	}
	
	
	public Boolean checkBranchAvailability(Integer branchId)
	{
		return branchRepository.findById(branchId).isPresent()? true:false;
	}
	
	public Boolean checkVehicleAvailabilityUnderBranch(Integer branchId, UUID vehicleId)
	{
		if(checkBranchAvailability(branchId))
		{
			
			Branch branch = branchRepository.findById(branchId).get();
			boolean found = false;
			List<Bike> bikes = branch.getBikes();
			List<Car> cars = branch.getCars();
			for(int i=0;i<bikes.size();i++)
			{
				if(bikes.get(i).getId().compareTo(vehicleId)==0)
				{
					found = true;
					break;
				}
			}
			
			if(!found)
			{
				for(int i=0;i<cars.size();i++)
				{
					if(cars.get(i).getId().compareTo(vehicleId)==0)
					{
						found = true;
						break;
					}
				}
				
			}
				return found;
		}
		else
			return false;
		
	}
	
	public Boolean checkAvailability(AddBookingRequest addBookingRequest)
	{
		Integer branchId = addBookingRequest.getBranchId();
		UUID vehicleId = addBookingRequest.getVehicleId();		
		if(checkVehicleAvailabilityUnderBranch(branchId,vehicleId))
		{
			List<Booking> retrievedVehicleBookings = bookingRepository.findByVehicleId(vehicleId);
			if(retrievedVehicleBookings.size()>0)
			{
				for(int i=0;i<retrievedVehicleBookings.size();i++)
				{
					Booking booking = retrievedVehicleBookings.get(i);
					if(booking.getStatus() == BookingType.ACTIVE
							&& checkTimeOverlap(booking.getStartTime(),booking.getEndTime(),
									addBookingRequest.getStartTime(),addBookingRequest.getEndTime()))
						return false;
				}
			}
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public Boolean checkTimeOverlap(LocalTime currStartT, LocalTime currEndT, String givenStartT, String givenEndT )
	{
		LocalTime convertedGivenStartT = LocalTime.parse(givenStartT);
		LocalTime convertedGivenEndT = LocalTime.parse(givenEndT);
		if(convertedGivenEndT.isBefore(currStartT) || convertedGivenStartT.isAfter(currEndT))
			return false;
		else
			return true;
	}
	
	public Boolean scheduleBooking(AddBookingRequest addBookingRequest)
	{
		Integer branchId = addBookingRequest.getBranchId();
		String customerContactNo = addBookingRequest.getCustomerContactNo();
		UUID vehicleId = addBookingRequest.getVehicleId();
		LocalTime startTime = LocalTime.parse(addBookingRequest.getStartTime());
		LocalTime endTime = LocalTime.parse(addBookingRequest.getEndTime());
		BookingType status = BookingType.ACTIVE;
		
		Optional<Vehicle> carVehicle = carRepository.findById(vehicleId);
		Optional<Vehicle> bikeVehicle = bikeRepository.findById(vehicleId);
		
			if(carVehicle.isPresent())
			{
			    Integer amountPayable = calculatePayableAmount(carVehicle.get().getHourlyRentalPrice(),
			    		startTime,endTime);
				Booking incomingBooking = new Booking(branchId,customerContactNo,
						vehicleId,startTime,endTime,status,amountPayable);
				bookingRepository.save(incomingBooking);
				return true;
				
			}
			else if(bikeVehicle.isPresent())
			{
				Integer amountPayable = calculatePayableAmount(bikeVehicle.get().getHourlyRentalPrice(),
						startTime,endTime);
				Booking incomingBooking = new Booking(branchId,customerContactNo,
						vehicleId,startTime,endTime,status, amountPayable);
				bookingRepository.save(incomingBooking);
				return true;
			}
		
		return false;
	}
	
	public Integer calculatePayableAmount(Integer hourlyRentalPrice, LocalTime startTime, LocalTime endTime)
	{
		Long minutesBetween = ChronoUnit.MINUTES.between(startTime,endTime);
		return (minutesBetween.intValue()/60)*hourlyRentalPrice;
	}

	public Boolean checkRegisteredCustomer(String contactNo)
	{
		return customerRepository.findByContactNo(contactNo).size()>0? true:false;
	}

	public Boolean closeBooking(Integer bookingId,Integer branchId,String customerContactNo)
	{
		Optional<Booking> booking = bookingRepository.findById(bookingId);
		if(booking.isPresent())
		{
			Booking foundBooking = booking.get();
			if(foundBooking.getCustomerContactNo().compareTo(customerContactNo) == 0
				&& foundBooking.getBranchId() == branchId
				&& foundBooking.getStatus() == BookingType.ACTIVE)
			{
				foundBooking.setStatus(BookingType.CLOSE);
				bookingRepository.save(foundBooking);
				return true;
			}
		}
		return false;
	}
	
	public Boolean cancelBooking(Integer bookingId,Integer branchId,String customerContactNo)
	{
		Optional<Booking> booking = bookingRepository.findById(bookingId);
		if(booking.isPresent())
		{
			Booking foundBooking = booking.get();
			if(foundBooking.getCustomerContactNo().compareTo(customerContactNo) == 0 
				&& foundBooking.getBranchId() == branchId 
				&& foundBooking.getStatus() == BookingType.ACTIVE)
			{
				foundBooking.setStatus(BookingType.CANCEL);
				bookingRepository.save(foundBooking);
				return true;
			}
		}
		return false;
	}
	
	public List<Booking> getAllBookings()
	{
		List<Booking> bookings =  bookingRepository.findAll();
		logger.info("List of all bookings is of size {}",bookings.size());
		return bookings;
	}

	
}

