package com.kp.rentalservice.requests;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CloseBookingRequest {

	@NotEmpty
	String customerContactNo;
	@NotNull
	Integer branchId;
	@NotNull
	Integer bookingId;
	
}
