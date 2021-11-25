package com.bl.addressbook.model;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.bl.addressbook.dto.UserDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String name;
	private String mobileNumber;

	@OneToOne(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Address address;

	private LocalDateTime created_at;
	private LocalDateTime updated_at;

	public User(UserDTO user) {
		this.name = user.getName();
		this.mobileNumber = user.getMobileNumber();
		this.address = user.getAddress();
		this.created_at = LocalDateTime.now();
	}
}
