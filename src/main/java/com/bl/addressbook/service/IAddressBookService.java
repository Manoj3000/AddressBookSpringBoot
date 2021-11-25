package com.bl.addressbook.service;

import java.util.List;

import com.bl.addressbook.dto.UserDTO;
import com.bl.addressbook.model.User;

public interface IAddressBookService {

	List<User> getUsers();

	User getUser(long id);

	User addUser(UserDTO user);

	User editUser(UserDTO user);

	void deleteUser(long id);

}
