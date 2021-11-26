package com.bl.addressbook.service;

import java.util.List;

import com.bl.addressbook.dto.UserDTO;
import com.bl.addressbook.exception.UserNotFoundException;
import com.bl.addressbook.model.User;

public interface IAddressBookService {

	List<User> getUsers();

	User getUser(long id) throws UserNotFoundException;

	User addUser(UserDTO user);

	User editUser(long id, UserDTO user) throws UserNotFoundException;

	void deleteUser(long id) throws UserNotFoundException;

}
