package com.cee.userrole.exception;

public class BadRequestException extends Exception {
	private static final long serialVersionUID = 1L;

	public BadRequestException(String message) {
		super(message);
	}

	public BadRequestException(String message, Throwable th) {
		super(message, th);
	}

}
