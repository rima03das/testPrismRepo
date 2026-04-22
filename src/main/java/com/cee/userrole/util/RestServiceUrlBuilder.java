package com.cee.userrole.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RestServiceUrlBuilder {

	private static final Logger logger = LoggerFactory.getLogger(RestServiceUrlBuilder.class);

	@Value("${service.protocol}")
	private String protocol;
	private String serviceName;
	private String resourceUrl;

	public RestServiceUrlBuilder() {
		super();
	}

	public RestServiceUrlBuilder setProtocol(String protocol) {
		this.protocol = protocol;
		return this;
	}

	public RestServiceUrlBuilder setServiceName(String serviceName) {
		this.serviceName = serviceName;
		return this;
	}

	public RestServiceUrlBuilder setResourceUrl(String resourceUrl) {
		this.resourceUrl = resourceUrl;
		return this;
	}

	public String buildUrl() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.protocol).append("://").append(this.serviceName).append("/").append(this.resourceUrl);
		logger.debug(sb.toString());
		return sb.toString();
	}

}
