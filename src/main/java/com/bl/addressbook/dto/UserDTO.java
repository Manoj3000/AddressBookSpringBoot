package com.bl.addressbook.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.bl.addressbook.model.Address;

import lombok.Data;

@Data
public class UserDTO {
	
	@Pattern(regexp = "^[A-Z]{1}[a-zA-Z\\s]{2,}$", message = "Invalid User Name")
	private String name;

	@Pattern(regexp = "^[6-9]{1}[0-9]{9}$", message = "Invalid Mobile Number")
	private String mobileNumber;
	
	@NotNull(message = "Address should not be Empty")
	private Address address;
}