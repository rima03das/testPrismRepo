package com.cee.userrole.messages;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class GenericMessageBuilder<T> {

	private List<T> result;
	private String message;
	private Boolean error;
	private String details;

	public GenericMessageBuilder() {
		super();
	}

	/**
	 * @param result the result to set
	 */
	public GenericMessageBuilder<T> setResult(List<T> result) {
		this.result = result;
		return this;
	}

	/**
	 * @param message the message to set
	 */
	public GenericMessageBuilder<T> setMessage(String message) {
		this.message = message;
		return this;
	}

	/**
	 * @param error the error to set
	 */
	public GenericMessageBuilder<T> setError(Boolean error) {
		this.error = error;
		return this;
	}

	/**
	 * @param details the details to set
	 */
	public GenericMessageBuilder<T> setDetails(String details) {
		this.details = details;
		return this;
	}

	public ResponseEntity<GenericResponseMessage<T>> buildResponseEntity(HttpStatus httpStatus) {
		GenericResponseMessage<T> genericResponseMessage = new GenericResponseMessage<>();
		genericResponseMessage.setResult(this.result);
		genericResponseMessage.setMessage(this.message);
		genericResponseMessage.setError(this.error);
		genericResponseMessage.setDetails(this.details);

		return new ResponseEntity<>(genericResponseMessage, httpStatus);

	}

}
