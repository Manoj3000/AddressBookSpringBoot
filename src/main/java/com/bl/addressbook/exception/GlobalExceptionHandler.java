package com.bl.addressbook.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler{

	@ExceptionHandler(value = UserNotFoundException.class)
	public ResponseEntity<String> userNotFoundException(UserNotFoundException userNotFoundException) {
		return new ResponseEntity<String>(userNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = UserAlreadyExistsException.class)
	public ResponseEntity<String> userAlreadyExistsException(UserAlreadyExistsException userAlreadyExistsException) {
		return new ResponseEntity<String>("User already exists", HttpStatus.CONFLICT);
	}

	public ResponseEntity<Object> databaseConnectionFailsException(Exception exception) {
		return new ResponseEntity<>("DB Connection Lost", HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
