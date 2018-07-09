package com.nelioalves.cursomc.services.exceptions;

public class AuthorizationException extends RuntimeException {

	private static final long serialVersionUID = 4384920945038295451L;

	public AuthorizationException(String message) {
		super(message);
	}

	public AuthorizationException(String message, Throwable cause) {
		super(message, cause);
	}
}
