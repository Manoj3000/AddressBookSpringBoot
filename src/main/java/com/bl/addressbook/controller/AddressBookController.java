package com.bl.addressbook.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bl.addressbook.dto.UserDTO;
import com.bl.addressbook.exception.UserNotFoundException;
import com.bl.addressbook.model.User;
import com.bl.addressbook.response.Response;
import com.bl.addressbook.service.IAddressBookService;

@RestController
public class AddressBookController {
	
	@Autowired
	IAddressBookService addressBookService;

	@GetMapping("/getUsers")
	public ResponseEntity<Response> getUsers() {
		List<User> users = addressBookService.getUsers();
		Response response = new Response("Get All Users Successfully",(long)200, users);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	
	@GetMapping("/getUser")
	public ResponseEntity<Response> getUser(@RequestHeader long id) throws UserNotFoundException {
		User user = addressBookService.getUser(id);
		Response response = new Response("Get User : " + id + " Successfully ",(long)200, user);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	
	@PostMapping("/addUser")
	public ResponseEntity<Response> addUser(@Valid @RequestBody UserDTO user) {
		User savedUser = addressBookService.addUser(user);
		Response response = new Response("Added User Successfully",(long)200, savedUser);
		return new ResponseEntity<Response>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/editUser")
	public ResponseEntity<Response> editUser(@RequestHeader long id, @Valid @RequestBody UserDTO user) throws UserNotFoundException {
		User updatedUser = addressBookService.editUser(id, user);
		Response response = new Response("Updated User : " + id + " Successfully",(long)200, updatedUser);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteUser")
	public ResponseEntity<Response> deleteUser(@RequestHeader long id) throws UserNotFoundException {
		addressBookService.deleteUser(id);
		Response response = new Response("Deleted User : " + id + " Successfully",(long)200, null);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}	
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getFieldErrors()
				.forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
		return errors;
	}
}
