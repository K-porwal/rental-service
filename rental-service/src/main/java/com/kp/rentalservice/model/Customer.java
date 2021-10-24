package com.kp.rentalservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Customer {

	@Id
	@GeneratedValue
	private Integer id;
	private String idProofNo;
	private String name;
	private String address;
	private String contactNo;
	
	public Customer(String idProofNo, String name, String address, String contactNo)
	{
		this.idProofNo = idProofNo;
		this.name = name;
		this.address = address;
		this.contactNo = contactNo;		
	}
}
