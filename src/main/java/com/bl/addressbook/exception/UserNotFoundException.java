package com.bl.addressbook.exception;

public class UserNotFoundException extends Exception {

	private String message;

	public UserNotFoundException(String message) {
		super(message);
		this.message = message;
	}

	public UserNotFoundException() {
	}

	public String getMessage() {
		return message;
	}
	
}
