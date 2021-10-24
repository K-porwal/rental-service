package com.kp.rentalservice.requests;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.kp.rentalservice.constants.VehicleType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddVehicleRequest {

	@NotEmpty
	private String registrationNo;
	
	@NotEmpty
	private String companyName;
	
	@NotEmpty
	private String colour;
	
	@NotNull
	private Integer hourlyRentalPrice;
	
	@NotNull
	private VehicleType vehicleType;
	
	@NotNull
	private Integer branchId;
	
	 
}
