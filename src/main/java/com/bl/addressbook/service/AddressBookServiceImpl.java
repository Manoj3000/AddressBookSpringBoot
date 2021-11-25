package com.bl.addressbook.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bl.addressbook.dto.UserDTO;
import com.bl.addressbook.model.Address;
import com.bl.addressbook.model.User;
import com.bl.addressbook.repository.AddressBookUserRepo;

@Service
public class AddressBookServiceImpl implements IAddressBookService {

	@Autowired
	private AddressBookUserRepo addressBookUserRepo;
	
	@Override
	public List<User> getUsers() {
		return addressBookUserRepo.findAll();
	}

	@Override
	public User getUser(long id) {
		return addressBookUserRepo.findById(id).get();
	}

	@Override
	public User addUser(UserDTO userDTO) {
		User user = new User(userDTO);
		Address address = userDTO.getAddress();
		address.setUser(user);
		return addressBookUserRepo.save(user);
	}

	@Override
	public User editUser(long id, UserDTO userDTO) {
	 	User getUser = addressBookUserRepo.findById(id).get();
	 	
	 	getUser.setName(userDTO.getName());
	 	getUser.setMobileNumber(userDTO.getMobileNumber());
	 	getUser.setUpdated_at(LocalDateTime.now());
	 	
		Address address = getUser.getAddress();

		address.setArea(userDTO.getAddress().getArea());
		address.setCity(userDTO.getAddress().getCity());
		address.setState(userDTO.getAddress().getState());
		address.setCountry(userDTO.getAddress().getCountry());
		address.setPincode(userDTO.getAddress().getPincode());
		
		address.setUser(getUser);
		
	 	getUser.setAddress(address);
	 	
		return addressBookUserRepo.save(getUser);	 	
	}

	@Override
	public void deleteUser(long id) {
		User user = addressBookUserRepo.findById(id).get();
		Address address = user.getAddress();
		addressBookUserRepo.delete(user);
	}

}
