package com.bl.addressbook.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bl.addressbook.dto.UserDTO;
import com.bl.addressbook.model.User;

@Service
public class AddressBookServiceImpl implements IAddressBookService {

	private List<User> users;
	
	public AddressBookServiceImpl() {
		this.users = new ArrayList<>();
	}
	
	@Override
	public List<User> getUsers() {
		return users;
	}

	@Override
	public User getUser(long id) {
		User user = null;
		for(User item : users) {
			if(item.getId() == id) {
				user = item;
				break;
			}
		}
		return user;
	}

	@Override
	public User addUser(UserDTO userDTO) {
		User user = new User(userDTO);
		users.add(user);
		return user;
	}

	@Override
	public User editUser(UserDTO userDTO) {
		User user = new User(userDTO);
		users.forEach(item -> {
			if(user.getId() == item.getId()) {
				item.setName(user.getName());
				item.setMobileNumber(user.getMobileNumber());
				item.setAddress(user.getAddress());
				item.setUpdated_at(LocalDateTime.now());
			}
		});
		return user;
	}

	@Override
	public void deleteUser(long id) {
		users.removeIf(user -> user.getId()==id);
	}

}
