package com.kp.rentalservice.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.kp.rentalservice.constants.BikeType;
import com.kp.rentalservice.constants.CarType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Branch {
	@Id
	@GeneratedValue
	private Integer id;
	
	private String name;
	
	private String city = "Varanasi";
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
	@JoinColumn(name = "branchId")
	private List<Car> cars = new ArrayList<>();
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "branchId")
	private List<Bike> bikes = new ArrayList<>();
	
	
	public Branch(String bname) {
		this.name = bname;
		for(int i=0;i<50;i++)
		{
			UUID vehicleId = UUID.randomUUID();
			
			if(i<30)
			{
				Bike bike = new Bike();
				bike.setId(vehicleId);
				bike.setColour("Blue");
				bike.setRegistrationNo("UP 65 AB"+i);
				bike.setCompanyName("Honda");
				
				if(i<10)
				{
					bike.setHourlyRentalPrice(100);
					bike.setEnginePower(BikeType.CC_100);
					
				}
				else if(i<20)
				{
					bike.setHourlyRentalPrice(150);
					bike.setEnginePower(BikeType.CC_125);
				}
				else
				{
					bike.setHourlyRentalPrice(200);
					bike.setEnginePower(BikeType.CC_150);
				}
				bikes.add(bike);
			}	
			
			else
			{
				
				Car c = new Car();
				c.setId(vehicleId);
				c.setColour("Blue");
				c.setRegistrationNo("UP 65 AB"+i);
				c.setCompanyName("Honda");
				c.setFuelVariant("Diesel");
				if(i<40)
				{
					
					c.setHourlyRentalPrice(400);
					c.setSeatingCapacity(CarType.CAP_5);
					
				}
				else
				{
					c.setHourlyRentalPrice(200);
					c.setSeatingCapacity(CarType.CAP_6);
				}
				cars.add(c);
			}
			
		}
	}
	
}	

