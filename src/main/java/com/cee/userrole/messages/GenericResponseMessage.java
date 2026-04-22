package com.cee.userrole.messages;

import java.util.List;

import com.cee.userrole.entities.View;
import com.fasterxml.jackson.annotation.JsonView;

public class GenericResponseMessage<T> {

	@JsonView(value = { View.UserRoleView.Query.class })
	private List<T> result;
	@JsonView(value = { View.UserRoleView.Query.class })
	private String message;
	@JsonView(value = { View.UserRoleView.Query.class })
	private Boolean error;
	private String details;

	public GenericResponseMessage() {
		super();
	}

	/**
	 * @param result
	 * @param message
	 * @param error
	 * @param serviceCode
	 * @param details
	 * @param httpStatus
	 */
	public GenericResponseMessage(List<T> result, String message, Boolean error, String details) {
		super();
		this.result = result;
		this.message = message;
		this.error = error;
		this.details = details;
	}

	public List<T> getResult() {
		return result;
	}

	public void setResult(List<T> result) {
		this.result = result;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Boolean getError() {
		return error;
	}

	public void setError(Boolean error) {
		this.error = error;
	}

	/**
	 * @return the details
	 */
	public String getDetails() {
		return details;
	}

	/**
	 * @param details the details to set
	 */
	public void setDetails(String details) {
		this.details = details;
	}

	@Override
	public String toString() {
		return super.toString();
	}

}
