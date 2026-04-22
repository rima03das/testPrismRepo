package cee.wfe.userrole.helper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.util.MultiValueMap;

import com.cee.userrole.util.constants.AppConstant;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MockMvcUtil {
	private MockMvcUtil() {
	}

	/**
	 * Method to set API call path
	 * 
	 * @param httpMethod
	 * @param path
	 * @return MockHttpServletRequestBuilder
	 */
	private static MockHttpServletRequestBuilder setExecutePath(final String httpMethod, final String path) {
		MockHttpServletRequestBuilder execute = null;

		if (httpMethod.equalsIgnoreCase(TestConstant.HTTP_GET)) {
			execute = get(path).contentType(MediaType.APPLICATION_JSON);
		} else if (httpMethod.equalsIgnoreCase(TestConstant.HTTP_POST)) {
			execute = post(path).contentType(MediaType.APPLICATION_JSON);
		} else if (httpMethod.equalsIgnoreCase(TestConstant.HTTP_DELETE)) {
			execute = delete(path).contentType(MediaType.APPLICATION_JSON);
		} else if (httpMethod.equalsIgnoreCase(TestConstant.HTTP_PATCH)) {
			execute = patch(path).contentType(MediaType.APPLICATION_JSON);
		} else {
			return null;
		}

		return execute;
	}

	/**
	 * Method to set API call path with path variables
	 * 
	 * @param httpMethod
	 * @param path
	 * @param pathVariable
	 * @return MockHttpServletRequestBuilder
	 */
	private static MockHttpServletRequestBuilder setExecutePathWithPathVariable(final String httpMethod,
			final String path, final String[] pathVariable) {
		if (pathVariable.length > 3) {
			return null;
		}

		if (httpMethod.equalsIgnoreCase(TestConstant.HTTP_GET)) {
			return setGetRequestMethodWithMultiplePathVariable(path, pathVariable);
		} else if (httpMethod.equalsIgnoreCase(TestConstant.HTTP_POST)) {
			return setPostRequestMethodWithMultiplePathVariable(path, pathVariable);
		} else if (httpMethod.equalsIgnoreCase(TestConstant.HTTP_DELETE)) {
			return setDeleteRequestMethodWithMultiplePathVariable(path, pathVariable);
		} else if (httpMethod.equalsIgnoreCase(TestConstant.HTTP_PATCH)) {
			return setPatchRequestMethodWithMultiplePathVariable(path, pathVariable);
		} else {
			return null;
		}
	}

	/**
	 * Set HTTP GET request path with multiple path variables (max three)
	 * 
	 * @param path
	 * @param pathVariable
	 * @return MockHttpServletRequestBuilder
	 */
	private static MockHttpServletRequestBuilder setGetRequestMethodWithMultiplePathVariable(final String path,
			final String[] pathVariable) {
		MockHttpServletRequestBuilder execute = null;

		if (pathVariable.length == 1) {
			execute = get(path, pathVariable[0]).contentType(MediaType.APPLICATION_JSON);
		} else if (pathVariable.length == 2) {
			execute = get(path, pathVariable[0], pathVariable[1]).contentType(MediaType.APPLICATION_JSON);
		} else {
			execute = get(path, pathVariable[0], pathVariable[1], pathVariable[2])
					.contentType(MediaType.APPLICATION_JSON);
		}

		return execute;
	}

	/**
	 * Set HTTP POST request path with multiple path variables (max three)
	 * 
	 * @param path
	 * @param pathVariable
	 * @return MockHttpServletRequestBuilder
	 */
	private static MockHttpServletRequestBuilder setPostRequestMethodWithMultiplePathVariable(final String path,
			final String[] pathVariable) {
		MockHttpServletRequestBuilder execute = null;

		if (pathVariable.length == 1) {
			execute = post(path, pathVariable[0]).contentType(MediaType.APPLICATION_JSON);
		} else if (pathVariable.length == 2) {
			execute = post(path, pathVariable[0], pathVariable[1]).contentType(MediaType.APPLICATION_JSON);
		} else {
			execute = post(path, pathVariable[0], pathVariable[1], pathVariable[2])
					.contentType(MediaType.APPLICATION_JSON);
		}

		return execute;
	}

	/**
	 * Set HTTP PUT request path with multiple path variables (max three)
	 * 
	 * @param path
	 * @param pathVariable
	 * @return MockHttpServletRequestBuilder
	 */
	private static MockHttpServletRequestBuilder setDeleteRequestMethodWithMultiplePathVariable(final String path,
			final String[] pathVariable) {
		MockHttpServletRequestBuilder execute = null;

		if (pathVariable.length == 1) {
			execute = delete(path, pathVariable[0]).contentType(MediaType.APPLICATION_JSON);
		} else if (pathVariable.length == 2) {
			execute = delete(path, pathVariable[0], pathVariable[1]).contentType(MediaType.APPLICATION_JSON);
		} else {
			execute = delete(path, pathVariable[0], pathVariable[1], pathVariable[2])
					.contentType(MediaType.APPLICATION_JSON);
		}

		return execute;
	}

	/**
	 * Set HTTP PATCH request path with multiple path variables (max three)
	 * 
	 * @param path
	 * @param pathVariable
	 * @return MockHttpServletRequestBuilder
	 */
	private static MockHttpServletRequestBuilder setPatchRequestMethodWithMultiplePathVariable(final String path,
			final String[] pathVariable) {
		MockHttpServletRequestBuilder execute = null;

		if (pathVariable.length == 1) {
			execute = patch(path, pathVariable[0]).contentType(MediaType.APPLICATION_JSON);
		} else if (pathVariable.length == 2) {
			execute = patch(path, pathVariable[0], pathVariable[1]).contentType(MediaType.APPLICATION_JSON);
		} else {
			execute = patch(path, pathVariable[0], pathVariable[1], pathVariable[2])
					.contentType(MediaType.APPLICATION_JSON);
		}

		return execute;
	}

	/**
	 * Method to create MockHttpServletRequestBuilder with required details.
	 * 
	 * @param httpMethod
	 * @param path
	 * @param pathVariable
	 * @param headers
	 * @param parameters
	 * @param request
	 * @return execute
	 * @throws JsonProcessingException
	 */
	private static MockHttpServletRequestBuilder getHttpMethod(final String httpMethod, final String path,
			final String[] pathVariable, final HttpHeaders headers, final MultiValueMap<String, String> parameters,
			final Object request) throws JsonProcessingException {
		MockHttpServletRequestBuilder execute = null;

		if (pathVariable != null && pathVariable.length > 0) {
			execute = setExecutePathWithPathVariable(httpMethod, path, pathVariable);
		} else {
			execute = setExecutePath(httpMethod, path);
		}

		if (execute == null) {
			return null;
		}

		if (headers != null && !headers.isEmpty()) {
			execute.headers(headers);
		}

		if (parameters != null && !parameters.isEmpty()) {
			execute.params(parameters);
		}

		if (request != null) {
			System.out.println("Request: " + new ObjectMapper().writeValueAsString(request));

			execute.content(new ObjectMapper().writeValueAsBytes(request));
		}

		return execute;
	}

	/**
	 * Mock method to perform mock API call
	 * 
	 * @param mockMvc
	 * @param httpMethod
	 * @param path
	 * @param pathVariable
	 * @param headers
	 * @param parameters
	 * @param request
	 * @param isSuccess
	 * @param statusCode
	 * @throws Exception
	 */
	public static void performMockApiCall(final MockMvc mockMvc, final String httpMethod, final String path,
			final String[] pathVariable, final HttpHeaders headers, final MultiValueMap<String, String> parameters,
			final Object request, final boolean isSuccess, final String statusCode) throws Exception {
		ResultActions result = null;
		MockHttpServletRequestBuilder execute;

		execute = getHttpMethod(httpMethod, path, pathVariable, headers, parameters, request);

		if (execute != null) {
			result = mockMvc.perform(execute).andDo(print());

			if (isSuccess) {
				result.andExpect(status().isOk());
				result.andExpect(new ResultMatcher() {
					@Override
					public void match(MvcResult result) throws Exception {
						String successStatusCode = parse(result.getResponse().getContentAsString());
						System.out.println("Status Code: " + successStatusCode);
						Assertions.assertEquals(AppConstant.ACTIVE_STATUS, statusCode);
					}
				});
			} else {
				result.andExpect(new ResultMatcher() {
					@Override
					public void match(MvcResult result) throws Exception {
						String errorStatusCode = parse(result.getResponse().getContentAsString());
						System.out.println("Status Code: " + errorStatusCode);
						Assertions.assertEquals(errorStatusCode.toString(), statusCode);
					}
				});
			}
		}
	}

	/**
	 * Method to parse response and return status code
	 * 
	 * @param json
	 * @return status code
	 * @throws JsonProcessingException
	 * @throws IOException
	 */
	private static String parse(String json) throws JsonProcessingException, IOException {
		String statusCode = "";

		JsonFactory factory = new JsonFactory();

		ObjectMapper mapper = new ObjectMapper(factory);
		JsonNode rootNode = mapper.readTree(json);

		Iterator<Map.Entry<String, JsonNode>> fieldsIterator = rootNode.fields();

		while (fieldsIterator.hasNext()) {
			Map.Entry<String, JsonNode> field = fieldsIterator.next();
			if (field.getKey().equals("status_code")) {
				statusCode = field.getValue().asText();
			}
		}

		return statusCode;
	}
}
