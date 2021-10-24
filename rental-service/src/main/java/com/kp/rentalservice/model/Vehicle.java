package com.kp.rentalservice.model;


import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Vehicle implements Comparable<Vehicle> {
	
	@Id
	protected UUID id;
	
	protected String registrationNo;
	
	protected String companyName;
	
	protected String colour;
	
	protected Integer hourlyRentalPrice;
	
	public int compareTo(Vehicle secondVehicle) {
	    
		return this.getHourlyRentalPrice().compareTo(secondVehicle.getHourlyRentalPrice());        
        
    }	
	
	
}
