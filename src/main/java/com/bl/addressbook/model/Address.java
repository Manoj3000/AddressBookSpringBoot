package com.bl.addressbook.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;

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
	
	@NotEmpty(message = "Area cannot be empty")
	private String area;
	
	@NotEmpty(message = "City cannot be empty")
	private String city;
	
	@NotEmpty(message = "State cannot be empty")
	private String state;
	
	@NotEmpty(message = "Country cannot be empty")
	private String country;
	
	@NotEmpty(message = "Pincode cannot be empty")
	private String pincode;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    @JsonBackReference
	private User user;
}
