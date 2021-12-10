package com.bl.addressbook.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
public class UserDTO {
	
	@Pattern(regexp = "^[A-Z]{1}[a-zA-Z\\s]{2,}$", message = "Invalid User Name")
	@NotBlank(message = "Name cannot be empty")
	private String name;

	@Pattern(regexp = "^[6-9]{1}[0-9]{9}$", message = "Invalid Mobile Number")
	@NotBlank(message = "Mobile Number cannot be empty")
	private String mobileNumber;

	@Pattern(regexp = "[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}", message = "Invalid Email")
	@NotBlank(message = "Email cannot be empty")
	private String email;
	
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$", message = "Invalid Mobile Password")
	@NotBlank(message = "Password cannot be empty")
	private String password;
	
	@NotNull(message = "Address should not be empty")
	@Valid
	private AddressDTO address;
}
