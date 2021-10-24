package com.kp.rentalservice.model;

import java.util.UUID;

import javax.persistence.Entity;

import com.kp.rentalservice.constants.CarType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Car extends Vehicle{
	
	private String fuelVariant;
	
	private CarType seatingCapacity;
	
	public Car(UUID id, String registrationNo, String companyName, String colour, Integer hourlyRentalPrice,
			String fuelVariant, CarType cap5) {
		
		this.id = id;
		this.registrationNo = registrationNo;
		this.companyName = companyName;
		this.colour = colour;
		this.hourlyRentalPrice = hourlyRentalPrice;
		this.fuelVariant = fuelVariant;
		this.seatingCapacity = cap5;
	}

}
