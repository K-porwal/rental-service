package com.kp.rentalservice.model;

import java.time.LocalTime;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.kp.rentalservice.constants.BookingType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Booking {

	@Id
	@GeneratedValue
	private Integer id;
	
	private Integer branchId;
	
	private String customerContactNo;
	
	private UUID vehicleId;
	
	private LocalTime startTime;
	
	private LocalTime endTime;
	
	private BookingType status;
	
	private Integer amountPayable;
	
	public Booking(Integer branchId, String customerContactNo, UUID vehicleId,
			LocalTime startTime,LocalTime endTime, BookingType status, Integer amountPayable) {
		this.branchId = branchId;
		this.customerContactNo = customerContactNo;
		this.vehicleId = vehicleId;
		this.startTime = startTime;
		this.endTime = endTime;
		this.status = status;
		this.amountPayable = amountPayable;
	}
}
