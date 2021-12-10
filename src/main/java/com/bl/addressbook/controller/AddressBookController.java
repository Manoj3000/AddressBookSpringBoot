package com.bl.addressbook.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bl.addressbook.dto.UserDTO;
import com.bl.addressbook.exception.LoginException;
import com.bl.addressbook.exception.UserNotFoundException;
import com.bl.addressbook.model.User;
import com.bl.addressbook.response.Response;
import com.bl.addressbook.service.IAddressBookService;
import com.bl.addressbook.util.TokenUtil;

@RestController
@CrossOrigin
public class AddressBookController {
	
	@Autowired
	IAddressBookService addressBookService;
	
	@Autowired
	TokenUtil tokenutil;

	@GetMapping("/login")
	public ResponseEntity<Response> checkUser(@RequestHeader String email, @RequestHeader String password) throws UserNotFoundException, LoginException{
		String isLogin = addressBookService.checkUser(email, password);
		Response response = new Response("Login successfully",(long)200, isLogin);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	
	@GetMapping("/getUsers")
	public ResponseEntity<Response> getUsers(@RequestHeader String isLogin) throws LoginException {
		List<User> users = addressBookService.getUsers(isLogin);
		Response response = new Response("Get all users successfully",(long)200, users);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	
	@GetMapping("/getUser")
	public ResponseEntity<Response> getUser(@RequestHeader String isLogin, @RequestHeader long id) throws UserNotFoundException, LoginException {
		User user = addressBookService.getUser(isLogin, id);
		Response response = new Response("Get user successfully ",(long)200, user);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	
	@PostMapping("/addUser")
	public ResponseEntity<Response> addUser(@RequestHeader String isLogin, @Valid @RequestBody UserDTO user) throws LoginException {
		User savedUser = addressBookService.addUser(isLogin, user);
		Response response = new Response("Added user successfully",(long)200, tokenutil.createToken(savedUser.getId()));
		return new ResponseEntity<Response>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/editUser")
	public ResponseEntity<Response> editUser(@RequestHeader String isLogin, @RequestHeader long id, @Valid @RequestBody UserDTO user) throws UserNotFoundException, LoginException {
		User updatedUser = addressBookService.editUser(isLogin, id, user);
		Response response = new Response("Updated user successfully",(long)200, updatedUser);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteUser")
	public ResponseEntity<Response> deleteUser(@RequestHeader String isLogin, @RequestHeader long id) throws UserNotFoundException, LoginException {
		addressBookService.deleteUser(isLogin,id);
		Response response = new Response("Deleted user successfully",(long)200, null);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteUsers")
	public ResponseEntity<Response> deleteUsers(@RequestHeader String isLogin, @RequestHeader List<Long> ids) throws UserNotFoundException, LoginException {
		addressBookService.deleteUsers(isLogin,ids);
		Response response = new Response("Deleted users successfully",(long)200, null);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteAll")
	public ResponseEntity<Response> deleteAllUser(@RequestHeader String isLogin) throws LoginException {
		addressBookService.deleteAllUser(isLogin);
		Response response = new Response("Deleted all users Successfully",(long)200, null);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	
	@GetMapping("/findByCity")
	public ResponseEntity<Response> findUserByCity(@RequestHeader String isLogin, @RequestHeader String city) throws LoginException{
		List<User> users = addressBookService.findUserByCity(isLogin, city);
		Response response = new Response("Get users from " + city,(long) 200, users);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	
	@GetMapping("/findByName")
	public ResponseEntity<Response> findUserByName(@RequestHeader String isLogin, @RequestHeader String name) throws LoginException{
		List<User> users = addressBookService.findUserByName(isLogin, name);
		Response response = new Response("Get users Successfully",(long) 200, users);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	
	@GetMapping("/findByPincode")
	public ResponseEntity<Response> findUserByPincode(@RequestHeader String isLogin, @RequestHeader String pincode) throws LoginException{
		List<User> users = addressBookService.findUserByPincode(isLogin, pincode);
		Response response = new Response("Get users Successfully",(long) 200, users);
		return new ResponseEntity<Response>(response, HttpStatus.OK);	
	}
	
//	http://localhost:8095/sortBy/asc/name
//	http://localhost:8095/sortBy/desc/mobileNumber
	
//	http://localhost:8095/sortBy/asc/address.state
//	http://localhost:8095/sortBy/desc/address.city
	
	@GetMapping("/sortBy/{sortDir}/{key}")
	public ResponseEntity<Response> sortUser(@RequestHeader String isLogin,@PathVariable String sortDir, @PathVariable String key) throws LoginException{
		List<User> users = null;
		if(sortDir.equals("asc")) {
			users = addressBookService.sortAscUser(isLogin, key);			
		}else if(sortDir.equals("desc")){
			users = addressBookService.sortDescUser(isLogin, key);				
		}
		Response response = new Response("Get users Successfully",(long) 200, users);
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
