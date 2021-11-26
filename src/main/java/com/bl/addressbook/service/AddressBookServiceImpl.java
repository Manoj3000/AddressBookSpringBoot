package com.bl.addressbook.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bl.addressbook.dto.UserDTO;
import com.bl.addressbook.model.Address;
import com.bl.addressbook.model.User;
import com.bl.addressbook.repository.AddressBookUserRepo;
import com.bl.addressbook.exception.UserNotFoundException;
import com.bl.addressbook.exception.UserAlreadyExistsException;

@Service
public class AddressBookServiceImpl implements IAddressBookService {

	@Autowired
	private AddressBookUserRepo addressBookUserRepo;
	
	@Override
	public List<User> getUsers() {
		return addressBookUserRepo.findAll();
	}

	@Override
	public User getUser(long id) throws UserNotFoundException {
		if(addressBookUserRepo.existsById(id)) {
			return addressBookUserRepo.findById(id).get();			
		}else {
			throw new UserNotFoundException("User Not Found");
		}
	}

	@Override
	public User addUser(UserDTO userDTO) {
		if(addressBookUserRepo.existsByUsermobileNumber(userDTO.getMobileNumber()) == null) {
			User user = new User(userDTO);
			
			Address address = userDTO.getAddress();
			address.setArea(userDTO.getAddress().getArea());
			address.setCity(userDTO.getAddress().getCity());
			address.setState(userDTO.getAddress().getState());
			address.setCountry(userDTO.getAddress().getCountry());
			address.setPincode(userDTO.getAddress().getPincode());
			address.setUser(user);
			
			user.setAddress(address);
			return addressBookUserRepo.save(user);			
		}else {
			throw new UserAlreadyExistsException();
		}
	}

	@Override
	public User editUser(long id, UserDTO userDTO) throws UserNotFoundException {
		if(addressBookUserRepo.findById(id).isPresent()) {
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
		}else {
			throw new UserNotFoundException("User Not Found");
		}
	 	
	}

	@Override
	public void deleteUser(long id) throws UserNotFoundException {
		
		if(addressBookUserRepo.findById(id).isPresent()) {
			User user = addressBookUserRepo.findById(id).get();
			addressBookUserRepo.delete(user);
		}else {
			throw new UserNotFoundException("User Not Found");			
		}
		
	}

}
