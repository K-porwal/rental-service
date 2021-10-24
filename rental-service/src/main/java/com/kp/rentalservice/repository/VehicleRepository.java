package com.kp.rentalservice.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.kp.rentalservice.model.Vehicle;

@NoRepositoryBean
public interface VehicleRepository<T extends Vehicle> extends JpaRepository<Vehicle, UUID> {

}
