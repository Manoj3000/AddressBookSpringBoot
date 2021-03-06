package com.bl.addressbook.model;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.bl.addressbook.dto.AddressDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long address_id;
	
	private String area;
	private String street;
	private String city;
	private String state;
	private String country;
	private String pincode;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    @JsonBackReference
	private User user;
    
	private LocalDateTime created_at;
	private LocalDateTime updated_at;

	public Address(AddressDTO address) {
		this.area = address.getArea();
		this.street = address.getStreet();
		this.city = address.getCity();
		this.state = address.getState();
		this.country = address.getCountry();
		this.pincode = address.getPincode();
		this.created_at = LocalDateTime.now();
	}
}
