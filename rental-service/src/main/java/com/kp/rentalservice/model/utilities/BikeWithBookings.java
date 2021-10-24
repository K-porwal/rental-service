package com.kp.rentalservice.model.utilities;

import java.util.List;

import com.kp.rentalservice.model.Bike;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BikeWithBookings {
  private Bike bike;
  private List<TimeRange> bookedTimings;
  
  public BikeWithBookings(Bike bike)
  {
	  this.bike = bike;
  }
}
