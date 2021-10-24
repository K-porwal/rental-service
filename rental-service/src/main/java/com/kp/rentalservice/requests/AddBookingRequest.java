package com.kp.rentalservice.requests;

import java.util.UUID;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddBookingRequest {

	@NotNull
	private Integer branchId;
	@NotNull
	private String customerContactNo;
	@NotEmpty
	private UUID vehicleId;
	@NotEmpty
	private String startTime;
	@NotEmpty
	private String endTime;
}
