//package cee.wfe.userrole;
//
//import static org.hamcrest.Matchers.containsString;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import java.util.Arrays;
//
//import com.cee.userrole.WfeUserRole;
//import com.cee.userrole.entities.UserRoleEntity;
//import com.cee.userrole.util.StatusType;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.util.LinkedMultiValueMap;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = WfeUserRole.class)
//@AutoConfigureMockMvc
//@PropertySource("classpath:application.properties")
//public class WfeUserRoleTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Test
//    public void shouldReturnDefaultMessage() throws Exception {
//        this.mockMvc.perform(get("/api/v2/userrole/")).andDo(print()).andExpect(status().isOk())
//                .andExpect(content().string(containsString("User Roles service")));
//	}
//	
//	@Test
//	public void shouldSaveUserRole() throws Exception {
//        
//        // Request Header
//        LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
//        requestParams.add("applId", "1");
//
//		// create object for saving
//        UserRoleEntity roleEntry = new UserRoleEntity(
//			"1", StatusType.ACTIVE.name(), "annonymous", Arrays.asList("1", "2")
//		);
//        
//        this.mockMvc
//        .perform(
//             post("/api/v2/userrole/save")
//            .contentType(MediaType.APPLICATION_JSON)
//            .content(this.asJsonString(roleEntry))
//            .params(requestParams)
//            .header("token", "devuser@example.com")
//        )
//		.andDo(print()).andExpect(status().isOk())
//		.andExpect(jsonPath("$.result[0].status").value("ACTIVE"));
//    }
//
//	@Test
//	public void shouldReturnBlankRoleError_saveUserRole() throws Exception {
//        
//        // Request Header
//        LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
//        requestParams.add("applId", "1");
//
//		// create object for saving
//        UserRoleEntity roleEntry = new UserRoleEntity(
//			"1", StatusType.ACTIVE.name(), "", Arrays.asList("1", "2")
//		);
//        
//        this.mockMvc
//        .perform(
//             post("/api/v2/userrole/save")
//            .contentType(MediaType.APPLICATION_JSON)
//            .content(this.asJsonString(roleEntry))
//            .params(requestParams)
//            .header("token", "devuser@example.com")
//        )
//		.andDo(print()).andExpect(status().isBadRequest())
//		.andExpect(jsonPath("$.message").value("Role can not be empty"));
//	}
//	@Test
//	public void shouldReturnBlankApplicationIdError_saveUserRole() throws Exception {
//        
//        // Request Header
//        LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
//        requestParams.add("applId", "1");
//
//		// create object for saving
//        UserRoleEntity roleEntry = new UserRoleEntity(
//			"", StatusType.ACTIVE.name(), "annonymous", Arrays.asList("1", "2")
//		);
//        
//        this.mockMvc
//        .perform(
//             post("/api/v2/userrole/save")
//            .contentType(MediaType.APPLICATION_JSON)
//            .content(this.asJsonString(roleEntry))
//            .params(requestParams)
//            .header("token", "devuser@example.com")
//        )
//		.andDo(print()).andExpect(status().isBadRequest())
//		.andExpect(jsonPath("$.message").value("Application Id can not be empty"));
//	}
//	@Test
//	public void shouldReturnBlankTokenError_saveUserRole() throws Exception {
//        
//        // Request Header
//        LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
//        requestParams.add("applId", "1");
//
//		// create object for saving
//        UserRoleEntity roleEntry = new UserRoleEntity(
//			"1", StatusType.ACTIVE.name(), "annonymous", Arrays.asList("1", "2")
//		);
//        
//        this.mockMvc
//        .perform(
//             post("/api/v2/userrole/save")
//            .contentType(MediaType.APPLICATION_JSON)
//            .content(this.asJsonString(roleEntry))
//            .params(requestParams)
//        )
//		.andDo(print()).andExpect(status().isBadRequest());
//	}
//    @Test
//	public void shouldReturnUserRoleByRoleId() throws Exception {
//        LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
//        requestParams.add("roleId", "5f51b4bf17177aa10bce6eab");
//        this.mockMvc
//        .perform(get("/api/v2/userrole/fetch/role/5f51b4bf17177aa10bce6eab").params(requestParams).header("token", "devuser@example.com"))
//        .andDo(print())
//        .andExpect(status().isOk())
//		.andExpect(jsonPath("$.result[0].roleId").value("5f51b4bf17177aa10bce6eab"))
//		.andExpect(jsonPath("$.result[0].role").value("annonymous"));
//    }
//	@Test
//	public void shouldReturnRoleIdNotFoundError_findUserRoleByRoleId() throws Exception {
//        LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
//        requestParams.add("roleId", "5f51b4bf17177aa10bce6eab");
//        this.mockMvc
//        .perform(
//			get("/api/v2/userrole/fetch/role/5f51b4bf17177aa10bce6eab")
//			.params(requestParams)
//			.header("token", "devuser@example.com")
//		)
//        .andDo(print())
//        .andExpect(status().isBadRequest())
//		.andExpect(jsonPath("$.message").value("Please specify correct role id"));
//    }
//    @Test
//	public void shouldReturnUserRoleByApplId() throws Exception {
//        LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
//        requestParams.add("applId", "1");
//        this.mockMvc
//        .perform(get("/api/v2/userrole/fetch/appl/1").params(requestParams).header("token", "devuser@example.com"))
//        .andDo(print())
//        .andExpect(status().isOk());
//    }
//	@Test
//	public void shouldDeleteUserRoleByRoleId() throws Exception {
//        LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
//        requestParams.add("roleId", "5f51b4bf17177aa10bce6eab");
//        this.mockMvc
//        .perform(
//			 delete("/api/v2/userrole/delete/role/5f51b4bf17177aa10bce6eab")
//			.params(requestParams)
//			.header("token", "devuser@example.com")
//		)
//        .andDo(print())
//        .andExpect(status().isOk())
//        .andExpect(jsonPath("$.result[0].status").value("INACTIVE"));
//	}
//	@Test
//	public void shouldReturnRoleIdNotFoundError_deleteUserRoleByRoleId() throws Exception {
//        LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
//        requestParams.add("roleId", "5f51b4bf17177aa10bce6eab");
//        this.mockMvc
//        .perform(
//			 delete("/api/v2/userrole/delete/role/5f51b4bf17177aa10bce6eab")
//			.params(requestParams)
//			.header("token", "devuser@example.com")
//		)
//        .andDo(print())
//        .andExpect(status().isBadRequest())
//		.andExpect(jsonPath("$.message").value("Please specify correct role id"));
//	}
//    private String asJsonString(final Object obj) {
//        try {
//            return new ObjectMapper().writeValueAsString(obj);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//}