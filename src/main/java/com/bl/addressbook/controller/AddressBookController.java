package com.bl.addressbook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bl.addressbook.dto.UserDTO;
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
	
	@GetMapping("/getUser/{id}")
	public ResponseEntity<Response> getUser(@PathVariable long id) {
		User user = addressBookService.getUser(id);
		Response response = new Response("Get User : " + id + " Successfully ",(long)200, user);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	
	@PostMapping("/addUser")
	public ResponseEntity<Response> addUser(@RequestBody UserDTO user) {
		User savedUser = addressBookService.addUser(user);
		Response response = new Response("Added User Successfully",(long)200, savedUser);
		return new ResponseEntity<Response>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/editUser/{id}")
	public ResponseEntity<Response> editUser(@PathVariable long id, @RequestBody UserDTO user) {
		User updatedUser = addressBookService.editUser(user);
		Response response = new Response("Updated User : " + id + " Successfully",(long)200, updatedUser);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteUser/{id}")
	public ResponseEntity<Response> deleteUser(@PathVariable long id) {
		addressBookService.deleteUser(id);
		Response response = new Response("Deleted User : " + id + " Successfully",(long)200, null);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}	
}
