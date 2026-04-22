package cee.wfe.userrole.controllers;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.HttpHeaders;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.cee.userrole.controller.UserRoleController;
import com.cee.userrole.models.UserRoleModel;
import com.cee.userrole.services.UserRoleService;
import com.cee.userrole.util.constants.AppConstant;

import cee.wfe.userrole.helper.MockMvcUtil;
import cee.wfe.userrole.helper.TestConstant;
import cee.wfe.userrole.helper.TestUserRoleStub;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

//@SpringBootTest
@AutoConfigureMockMvc
public class UserRoleControllerTest {

	@Autowired
	private MockMvc mockMvc;

	private UserRoleController userRoleController;

	@Mock
	private UserRoleService userRoleService;

	HttpSession httpSession;
	HttpServletRequest request;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		this.userRoleController = new UserRoleController(userRoleService);
		request = new MockHttpServletRequest();
		this.mockMvc = MockMvcBuilders.standaloneSetup(userRoleController).setControllerAdvice(userRoleService).build();
	}

//    @Test
//    public void testFetchUserRolesByApplId() throws Exception {
// 
//        RequestBuilder requestBuilder = MockMvcRequestBuilders
//                .get("/api/v2/userrole/")
//                .contentType(MediaType.ALL);
//                
//
//        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//        MockHttpServletResponse response = result.getResponse();
//
//        assertEquals(HttpStatus.OK.value(), response.getStatus());
//
//    }

	@Test
	public void test_createUserRole_success() throws Exception {
		HttpHeaders headers = new HttpHeaders();
		headers.add("token", "shadab@user.com");

		UserRoleModel model = TestUserRoleStub.getStubbedUserRoleModel();

		String[] pathVariable = { "" };

		MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
		parameters.add("token", "shadab@user.com");

		MockMvcUtil.performMockApiCall(mockMvc, TestConstant.HTTP_POST,
				"/api/v2/application/" + model.getApplicationId() + "/user-role/", pathVariable, headers, parameters,
				model, true, AppConstant.ACTIVE_STATUS);

	}

	@Test
	public void test_updateUserRole_success() throws Exception {
		HttpHeaders headers = new HttpHeaders();
		headers.add("token", "shadab@user.com");

		UserRoleModel model = TestUserRoleStub.getStubbedUserRoleModel();

		String[] pathVariable = { "" };

		MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
		parameters.add("token", "shadab@user.com");

		MockMvcUtil.performMockApiCall(mockMvc, TestConstant.HTTP_PATCH,
				"/api/v2/application/" + model.getApplicationId() + "/user-role/", pathVariable, headers, parameters,
				model, true, AppConstant.ACTIVE_STATUS);

	}

	@Test
	public void test_fetchUserRoles_success() throws Exception {
		HttpHeaders headers = new HttpHeaders();
		headers.add("token", "shadab@user.com");

		UserRoleModel model = TestUserRoleStub.getStubbedUserRoleModel();

		String[] pathVariable = { "" };

		MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
		parameters.add("token", "shadab@user.com");
		parameters.add("roleId", "roleId");

		MockMvcUtil.performMockApiCall(mockMvc, TestConstant.HTTP_GET,
				"/api/v2/application/" + model.getApplicationId() + "/user-role/", pathVariable, headers, parameters,
				model, true, AppConstant.ACTIVE_STATUS);

	}

	@Test
	public void test_fetchUserRoles_successEmptyRoleId() throws Exception {
		HttpHeaders headers = new HttpHeaders();
		headers.add("token", "shadab@user.com");

		UserRoleModel model = TestUserRoleStub.getStubbedUserRoleModel();

		String[] pathVariable = { "" };

		MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
		parameters.add("token", "shadab@user.com");
		parameters.add("roleId", " ");

		MockMvcUtil.performMockApiCall(mockMvc, TestConstant.HTTP_GET,
				"/api/v2/application/" + model.getApplicationId() + "/user-role/", pathVariable, headers, parameters,
				model, true, AppConstant.ACTIVE_STATUS);

	}

	@Test
	public void test_deleteUserRoles_success() throws Exception {
		HttpHeaders headers = new HttpHeaders();
		headers.add("token", "shadab@user.com");

		UserRoleModel model = TestUserRoleStub.getStubbedUserRoleModel();

		String[] pathVariable = { "" };

		MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
		parameters.add("token", "shadab@user.com");
		parameters.add("roleId", "roleId");

		MockMvcUtil.performMockApiCall(mockMvc, TestConstant.HTTP_DELETE,
				"/api/v2/application/" + model.getApplicationId() + "/user-role/", pathVariable, headers, parameters,
				model, true, AppConstant.ACTIVE_STATUS);

	}

	@Test
	public void test_saveUserRoles_success() throws Exception {
		HttpHeaders headers = new HttpHeaders();
		headers.add("token", "shadab@user.com");

		List<String> list = new ArrayList<String>();
		list.add("workflowSeq");

		UserRoleModel model = TestUserRoleStub.getStubbedUserRoleModel();

		String[] pathVariable = { "" };

		MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
		parameters.add("token", "shadab@user.com");

		MockMvcUtil.performMockApiCall(mockMvc, TestConstant.HTTP_POST,
				"/api/v2/application/" + model.getApplicationId() + "/user-role/update/"
						+ model.getWorkflowSequenceIds().get(0),
				pathVariable, headers, parameters, list, true, AppConstant.ACTIVE_STATUS);

	}

	@Test
	public void test_fetchUserRoleForWF_success() throws Exception {
		HttpHeaders headers = new HttpHeaders();
		headers.add("token", "shadab@user.com");

		UserRoleModel model = TestUserRoleStub.getStubbedUserRoleModel();

		String[] pathVariable = { "" };

		MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
		parameters.add("token", "shadab@user.com");

		MockMvcUtil.performMockApiCall(mockMvc, TestConstant.HTTP_GET,
				"/api/v2/application/" + model.getApplicationId() + "/user-role/fetch/"
						+ model.getWorkflowSequenceIds().get(0),
				pathVariable, headers, parameters, model, true, AppConstant.ACTIVE_STATUS);

	}

}