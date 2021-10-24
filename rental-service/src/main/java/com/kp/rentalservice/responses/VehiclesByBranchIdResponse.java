package com.kp.rentalservice.responses;

import java.util.List;

import com.kp.rentalservice.model.utilities.BikeWithBookings;
import com.kp.rentalservice.model.utilities.CarWithBookings;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VehiclesByBranchIdResponse {

	private Integer branchId;
	private List <CarWithBookings> carsWithBookings;
	private List <BikeWithBookings> bikesWithbookings;
}
