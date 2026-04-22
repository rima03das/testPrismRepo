package com.cee.userrole.config;

import java.util.Optional;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;

public class AuditorAwareConfiguration implements AuditorAware<String> {
	
	@Autowired
	private HttpServletRequest request;

	@Override
	public Optional<String> getCurrentAuditor() {		
		String userName = request.getHeader("token");
		return Optional.of(userName);
	}

}
