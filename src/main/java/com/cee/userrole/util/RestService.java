package com.cee.userrole.util;

import java.util.HashMap;

import com.cee.userrole.exception.RestServiceException;
import com.cee.userrole.messages.GenericResponseMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class RestService {
	
	private static final Logger logger = LoggerFactory.getLogger(RestService.class);
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private RestServiceUrlBuilder urlBuiilder;

	public <T> ResponseEntity<GenericResponseMessage<T>> invokeService(String applicationName, String endUrl,
			HttpMethod method, HttpEntity<?> requestEntity, Class<T> _class,
			HashMap<Class<T>, ParameterizedTypeReference<GenericResponseMessage<T>>> paramTypeRefMap, Object... var)
			throws RestServiceException {
		String url = urlBuiilder.setServiceName(applicationName).setResourceUrl(endUrl).buildUrl();
		System.out.println("rest service url :"+ url);
		ResponseEntity<GenericResponseMessage<T>> response = new ResponseEntity<GenericResponseMessage<T>>(
				HttpStatus.OK);
		try {
			StringBuilder sbAPIInfo = new StringBuilder();
			sbAPIInfo.append("Calling API: ").append("< ").append(method).append(" ").append(url).append(">")
					.append(" request data ").append(requestEntity);

			logger.debug(sbAPIInfo.toString());
//			((HttpComponentsClientHttpRequestFactory) restTemplate.getRequestFactory()).setConnectTimeout(5000000);
//			((HttpComponentsClientHttpRequestFactory) restTemplate.getRequestFactory()).setReadTimeout(5000000);

			ParameterizedTypeReference<GenericResponseMessage<T>> typeRef = paramTypeRefMap.get(_class);
			response = restTemplate.exchange(url, method, requestEntity, typeRef, var);
			if (response.getBody().getResult() != null) {

				StringBuilder sbResultType = new StringBuilder();
				sbResultType.append("Result Type of API : ").append("< ").append(method).append(" ").append(url)
						.append(">").append(response.getBody().getResult().getClass());
				logger.debug(sbResultType.toString());

				StringBuilder sbResult = new StringBuilder();
				sbResult.append("Result of API : ").append("< ").append(method).append(" ").append(url).append(">")
						.append(response.getBody().getResult());
				logger.debug(sbResult.toString());
			}
		} catch (RestClientException e) {
			e.printStackTrace();
			StringBuilder sb = new StringBuilder();
			sb.append("Invoke service Error for API ").append("< ").append(method).append(" ").append(url).append(">");
			logger.error(sb.toString(), e);
			throw new RestServiceException(sb.toString(), e);
		}
		return response;
	}

}

