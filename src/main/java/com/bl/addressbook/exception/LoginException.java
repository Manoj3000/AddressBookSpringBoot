package com.bl.addressbook.exception;

public class LoginException extends Exception {

	private String message;

	public LoginException(String message) {
		super(message);
		this.message = message;
	}

	public LoginException() {
	}

	public String getMessage() {
		return message;
	}
	
}
