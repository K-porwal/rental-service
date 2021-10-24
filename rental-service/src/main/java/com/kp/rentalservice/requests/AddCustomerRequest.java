package com.kp.rentalservice.requests;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddCustomerRequest {

	@NotEmpty
	String idProofNo;
	@NotEmpty
	String name;
	@NotEmpty
	String contactNo;
	@NotEmpty
	String address;
}
