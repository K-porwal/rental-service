package com.kp.rentalservice.model.utilities;

import java.util.List;

import com.kp.rentalservice.model.Car;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarWithBookings {
	private Car car;
	private List<TimeRange> bookedTimings;
	
	public CarWithBookings(Car car)
	{
		this.car = car;
	}
}
