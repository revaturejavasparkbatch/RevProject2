package com.revature.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="User has no saved fortunes!")
public class NoFortunesFoundException extends RuntimeException{

	public NoFortunesFoundException() {
		super();
	}
	
}
