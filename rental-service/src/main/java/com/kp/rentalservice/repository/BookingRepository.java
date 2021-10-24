package com.kp.rentalservice.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kp.rentalservice.constants.BookingType;
import com.kp.rentalservice.model.Booking;

public interface BookingRepository extends JpaRepository<Booking, Integer>{

	List <Booking> findByVehicleId(UUID id);
	
	List <Booking> findByStatus(BookingType bookingType);
}
