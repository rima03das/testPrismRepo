package com.cee.userrole.validator;

import com.cee.userrole.exception.BadRequestException;

import org.springframework.stereotype.Service;

@Service
public class RequestValidator {
	public void validateRequestHeader(final String token) throws BadRequestException {
		if(token == null || token== "") {
			throw new BadRequestException("Token missing in the request");
		}
	}

}
