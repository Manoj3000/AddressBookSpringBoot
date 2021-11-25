package com.bl.addressbook.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bl.addressbook.response.Response;

@RestController
public class AddressBookController {

	@GetMapping("/getUsers")
	public ResponseEntity<Response> getUsers() {
		Response response = new Response("Get All Users Successfully",(long)200, null);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	
	@GetMapping("/getUser/{id}")
	public ResponseEntity<Response> getUser(@PathVariable long id) {
		Response response = new Response("Get User : " + id + " Successfully ",(long)200, null);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	
	@PostMapping("/addUser")
	public ResponseEntity<Response> addUser() {
		Response response = new Response("Added User Successfully",(long)200, null);
		return new ResponseEntity<Response>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/editUser/{id}")
	public ResponseEntity<Response> editUser(@PathVariable long id) {
		Response response = new Response("Updated User : " + id + " Successfully",(long)200, null);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteUser/{id}")
	public ResponseEntity<Response> deleteUser(@PathVariable long id) {
		Response response = new Response("Deleted User : " + id + " Successfully",(long)200, null);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}	
}
