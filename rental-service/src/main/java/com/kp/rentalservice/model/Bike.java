package com.kp.rentalservice.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;

import com.kp.rentalservice.constants.BikeType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Bike extends Vehicle{
	

	@Enumerated
	@Column(name = "enginePower")
	private BikeType enginePower;
	
	public void setEnginePower(BikeType bikeType) {
		this.enginePower = bikeType;
	}

	public Bike(UUID id, String registrationNo, String companyName, String colour, Integer hourlyRentalPrice,
			BikeType bikeType) {
		this.id = id;
		this.registrationNo = registrationNo;
		this.companyName = companyName;
		this.colour = colour;
		this.hourlyRentalPrice = hourlyRentalPrice;
		this.enginePower = bikeType;
	}
}
