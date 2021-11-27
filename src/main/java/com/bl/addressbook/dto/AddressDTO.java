package com.bl.addressbook.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
public class AddressDTO {

	@NotBlank(message = "Area cannot be empty")
	private String area;
	
	@NotBlank(message = "City cannot be empty")
	private String city;
	
	@NotBlank(message = "State cannot be empty")
	private String state;
	
	@NotBlank(message = "Country cannot be empty")
	private String country;
	
	@NotBlank(message = "Pincode cannot be empty")
	@Pattern(regexp = "^[1-9][0-9]{5}$", message = "Invalid Pin Code")
	private String pincode;
	
}
