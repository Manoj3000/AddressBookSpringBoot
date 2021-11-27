package com.bl.addressbook.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
public class UserDTO {
	
	@Pattern(regexp = "^[A-Z]{1}[a-zA-Z\\s]{2,}$", message = "Invalid User Name")
	@NotBlank(message = "Name cannot be Empty")
	private String name;

	@Pattern(regexp = "^[6-9]{1}[0-9]{9}$", message = "Invalid Mobile Number")
	@NotBlank(message = "Mobile Number cannot be Empty")
	private String mobileNumber;
		
	@NotBlank(message = "User cannot be Empty")
	private String username;
	
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$", message = "Invalid Mobile Password")
	@NotBlank(message = "Password cannot be Empty")
	private String password;
	
	@NotNull(message = "Address should not be Empty")
	@Valid
	private AddressDTO address;
}
