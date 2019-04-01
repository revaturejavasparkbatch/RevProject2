package com.revature.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="No user with given email address!")
public class UserNotFoundException extends RuntimeException{

	public UserNotFoundException() {
		super();
	}
	
}
