package com.bl.addressbook.service;

import java.util.List;

import com.bl.addressbook.dto.UserDTO;
import com.bl.addressbook.exception.LoginException;
import com.bl.addressbook.exception.UserNotFoundException;
import com.bl.addressbook.model.User;

public interface IAddressBookService {

	List<User> getUsers(String isLogin) throws LoginException;

	User getUser(String isLogin, String token) throws LoginException, UserNotFoundException;

	User addUser(String isLogin, UserDTO user)  throws LoginException;

	User editUser(String isLogin, String token, UserDTO user) throws LoginException, UserNotFoundException;

	void deleteUser(String isLogin, String token) throws LoginException, UserNotFoundException;

	List<User> findUserByCity(String isLogin, String city) throws LoginException;

	List<User> findUserByName(String isLogin, String name) throws LoginException;

	List<User> findUserByPincode(String isLogin, String pincode) throws LoginException;

	void deleteAllUser(String isLogin) throws LoginException;

	List<User> sortAscUser(String isLogin, String key) throws LoginException;

	List<User> sortDescUser(String isLogin, String key) throws LoginException;

	String checkUser(String username, String password) throws UserNotFoundException, LoginException;



}
