package cee.wfe.userrole.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.util.ReflectionTestUtils;

import com.cee.userrole.entities.UserRoleEntity;
import com.cee.userrole.exception.BadRequestException;
import com.cee.userrole.models.UserRoleModel;
import com.cee.userrole.repositories.UserRoleRepository;
import com.cee.userrole.services.UserRoleService;
import com.cee.userrole.util.StatusType;

import cee.wfe.userrole.helper.TestUserRoleStub;

@AutoConfigureMockMvc
public class UserRoleServiceTest {

	@Mock
	UserRoleService userRoleService;
	@Mock
	UserRoleRepository userRoleRepository;
	@Mock
	MongoTemplate mongoTemplate;

	@BeforeEach
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

		this.userRoleService = new UserRoleService(mongoTemplate);

		ReflectionTestUtils.setField(userRoleService, "userRoleRepository", userRoleRepository);
		ReflectionTestUtils.setField(userRoleService, "mongoTemplate", mongoTemplate);
	}

	@AfterEach
	public void tearDown() throws Exception {
		this.userRoleService = null;
	}

	@Test
	public void test_validateAndCreateModel_success() throws Exception {
		// shadab
		UserRoleModel model = TestUserRoleStub.getStubbedUserRoleModel();

		userRoleService.validateAndCreateModel(model, model.getApplicationId());
	}

	@Test
	public void test_validateAndCreateModel_successBlankApplicationId() throws Exception {

		UserRoleModel model = TestUserRoleStub.getStubbedUserRoleModelBlankApplicationId();
		assertThrows(BadRequestException.class,
				() -> userRoleService.validateAndCreateModel(model, model.getApplicationId()));
	}

	@Test
	public void test_validateAndCreateModel_successBlankRole() throws Exception {
		// shadab
		UserRoleModel model = TestUserRoleStub.getStubbedUserRoleModelBlankRole();
		assertThrows(BadRequestException.class,
				() -> userRoleService.validateAndCreateModel(model, model.getApplicationId()));
	}

	@Test
	public void test_validateAndCreateModel_successBlankWorkflowSequenceIds() throws Exception {
		// shadab
		UserRoleModel model = TestUserRoleStub.getStubbedUserRoleModelBlankWorkflowSequenceIds();
		assertThrows(BadRequestException.class,
				() -> userRoleService.validateAndCreateModel(model, model.getApplicationId()));
	}

	@Test
	public void test_validateAndUpdateModel_success() throws Exception {
		// shadab
		UserRoleModel model = TestUserRoleStub.getStubbedUserRoleModel();

		userRoleService.validateAndUpdateModel(model, model.getApplicationId());
	}

	@Test
	public void test_validateAndUpdateModel_successBlankApplicationId() throws Exception {
		// shadab
		UserRoleModel model = TestUserRoleStub.getStubbedUserRoleModelBlankApplicationId();
		assertThrows(BadRequestException.class,
				() -> userRoleService.validateAndUpdateModel(model, model.getApplicationId()));
	}

	@Test
	public void test_validateAndUpdateModel_successBlankRole() throws Exception {
		// shadab
		UserRoleModel model = TestUserRoleStub.getStubbedUserRoleModelBlankRole();
		assertThrows(BadRequestException.class,
				() -> userRoleService.validateAndUpdateModel(model, model.getApplicationId()));
	}

	@Test
	public void test_validateAndUpdateModel_successBlankId() throws Exception {
		// shadab
		UserRoleModel model = TestUserRoleStub.getStubbedUserRoleModelBlankId();
		assertThrows(BadRequestException.class,
				() -> userRoleService.validateAndUpdateModel(model, model.getApplicationId()));
	}

	@Test
	public void test_saveUserRole_success() throws Exception {
		// shadab
		UserRoleEntity entity = TestUserRoleStub.getStubbedUserRoleEntity();
		Optional<UserRoleEntity> optional = Optional.of(entity);

		when(userRoleRepository.findByApplicatioIdAndRoleAndStatus(entity.getApplicationId(), entity.getRole(),
				StatusType.ACTIVE.name())).thenReturn(optional);

		userRoleService.saveUserRole(entity, entity.getApplicationId());
	}

	@Test
	public void test_fetchUserRoleByApplId_success() throws Exception {
		// shadab
		userRoleService.fetchUserRoleByApplId("CEE_TEST");
	}

	@Test
	public void test_fetchUserRoleByApplId_successBlankApplicationId() throws Exception {
		// shadab
		assertThrows(BadRequestException.class,
				() -> userRoleService.fetchUserRoleByApplId(" "));
	}

	@Test
	public void test_fetchUserRoleByRoleId_success() throws Exception {
		// shadab
		UserRoleEntity entity = TestUserRoleStub.getStubbedUserRoleEntity();
		Optional<UserRoleEntity> optional = Optional.of(entity);

		when(userRoleRepository.findByIdAndStatus(entity.getRole(), StatusType.ACTIVE.name())).thenReturn(optional);

		userRoleService.fetchUserRoleByRoleId(entity.getRole());
	}

	@Test
	public void test_fetchUserRoleByRoleId_successEmptyOptional() throws Exception {
		// shadab
		UserRoleEntity entity = TestUserRoleStub.getStubbedUserRoleEntity();
		Optional<UserRoleEntity> optional = Optional.empty();

		when(userRoleRepository.findByIdAndStatus(entity.getRole(), StatusType.ACTIVE.name())).thenReturn(optional);
		assertThrows(BadRequestException.class,
				() -> userRoleService.fetchUserRoleByRoleId(entity.getRole()));
	}

	@Test
	public void test_fetchUserRoleByRoleId_successBlankRole() throws Exception {
		// shadab
		UserRoleEntity entity = TestUserRoleStub.getStubbedUserRoleEntity();
		Optional<UserRoleEntity> optional = Optional.of(entity);

		when(userRoleRepository.findByIdAndStatus(entity.getRole(), StatusType.ACTIVE.name())).thenReturn(optional);
		assertThrows(BadRequestException.class,
				() -> userRoleService.fetchUserRoleByRoleId(" "));
	}

	@Test
	public void test_deleteUserRoleByRoleId_success() throws Exception {
		// shadab
		UserRoleEntity entity = TestUserRoleStub.getStubbedUserRoleEntity();
		Optional<UserRoleEntity> optional = Optional.of(entity);

		when(userRoleRepository.findByIdAndStatus(entity.getRole(), StatusType.ACTIVE.name())).thenReturn(optional);

		userRoleService.deleteUserRoleByRoleId(entity.getRole(), entity.getApplicationId());
	}

	@Test
	public void test_deleteUserRoleByRoleId_successEmptyOptional() throws Exception {
		// shadab
		UserRoleEntity entity = TestUserRoleStub.getStubbedUserRoleEntity();
		Optional<UserRoleEntity> optional = Optional.empty();

		when(userRoleRepository.findByIdAndStatus(entity.getRole(), StatusType.ACTIVE.name())).thenReturn(optional);
		assertThrows(BadRequestException.class,
				() -> userRoleService.deleteUserRoleByRoleId(entity.getRole(), entity.getApplicationId()));
	}

	@Test
	public void test_deleteUserRoleByRoleId_successBlankRole() throws Exception {
		// shadab
		UserRoleEntity entity = TestUserRoleStub.getStubbedUserRoleEntity();
		Optional<UserRoleEntity> optional = Optional.of(entity);

		when(userRoleRepository.findByIdAndStatus(entity.getRole(), StatusType.ACTIVE.name())).thenReturn(optional);
		assertThrows(BadRequestException.class,
				() -> userRoleService.deleteUserRoleByRoleId(" ", entity.getApplicationId()));
	}

	@Test
	public void test_updateRoleFromWorkflow_success() throws Exception {
		// shadab
		List<String> list = new ArrayList<String>();
		list.add("role");

		UserRoleEntity entity = TestUserRoleStub.getStubbedUserRoleEntity();
		Optional<UserRoleEntity> optional = Optional.of(entity);

		when(userRoleRepository.findByIdAndStatus(entity.getRole(), StatusType.ACTIVE.name())).thenReturn(optional);

		List<UserRoleEntity> entityList = new ArrayList<UserRoleEntity>();
		entityList.add(entity);

		when(userRoleRepository.findByApplicationIdAndStatus(entity.getApplicationId(), StatusType.ACTIVE.getName()))
				.thenReturn(entityList);

		userRoleService.updateRoleFromWorkflow(list, entity.getWorkflowSequenceIds().get(0), entity.getApplicationId());
	}

	@Test
	public void test_updateRoleFromWorkflow_successNotContainsWorkflowSeq() throws Exception {
		// shadab
		List<String> list = new ArrayList<String>();
		list.add("role");

		UserRoleEntity entity = TestUserRoleStub.getStubbedUserRoleEntity();
		Optional<UserRoleEntity> optional = Optional.of(entity);

		when(userRoleRepository.findByIdAndStatus(entity.getRole(), StatusType.ACTIVE.name())).thenReturn(optional);

		UserRoleEntity entity1 = TestUserRoleStub.getStubbedUserRoleEntityNotContainsWorkflowSeq();
		List<UserRoleEntity> entityList = new ArrayList<UserRoleEntity>();
		entityList.add(entity1);

		when(userRoleRepository.findByApplicationIdAndStatus(entity.getApplicationId(), StatusType.ACTIVE.getName()))
				.thenReturn(entityList);

		userRoleService.updateRoleFromWorkflow(list, "workflowSeq", entity.getApplicationId());
	}

	@Test
	public void test_updateRoleFromWorkflow_successEmptyOptionalNullUserRoleEntityList() throws Exception {
		// shadab
		List<String> list = new ArrayList<String>();
		list.add("role");

		UserRoleEntity entity = TestUserRoleStub.getStubbedUserRoleEntity();
		Optional<UserRoleEntity> optional = Optional.empty();

		when(userRoleRepository.findByIdAndStatus(entity.getRole(), StatusType.ACTIVE.name())).thenReturn(optional);

		when(userRoleRepository.findByApplicationIdAndStatus(entity.getApplicationId(), StatusType.ACTIVE.getName()))
				.thenReturn(null);

		userRoleService.updateRoleFromWorkflow(list, entity.getWorkflowSequenceIds().get(0), entity.getApplicationId());
	}

	@Test
	public void test_updateRoleFromWorkflow_successContainsId() throws Exception {
		// shadab
		List<String> list = new ArrayList<String>();
		list.add("id");

		UserRoleEntity entity = TestUserRoleStub.getStubbedUserRoleEntity();
		Optional<UserRoleEntity> optional = Optional.of(entity);

		when(userRoleRepository.findByIdAndStatus(entity.getRole(), StatusType.ACTIVE.name())).thenReturn(optional);

		List<UserRoleEntity> entityList = new ArrayList<UserRoleEntity>();
		entityList.add(entity);

		when(userRoleRepository.findByApplicationIdAndStatus(entity.getApplicationId(), StatusType.ACTIVE.getName()))
				.thenReturn(entityList);

		userRoleService.updateRoleFromWorkflow(list, entity.getWorkflowSequenceIds().get(0), entity.getApplicationId());
	}

	@Test
	public void test_fetchUserRoleForWF_success() throws Exception {
		// shadab
		UserRoleEntity entity = TestUserRoleStub.getStubbedUserRoleEntity();
		List<UserRoleEntity> entityList = new ArrayList<UserRoleEntity>();
		entityList.add(entity);

		when(userRoleRepository.findByApplicationIdAndStatus(entity.getApplicationId(), StatusType.ACTIVE.getName()))
				.thenReturn(entityList);

		userRoleService.fetchUserRoleForWF(entity.getWorkflowSequenceIds().get(0), entity.getApplicationId());
	}

	@Test
	public void test_fetchUserRoleForWF_successNullUserRoleEntityList() throws Exception {
		// shadab
		UserRoleEntity entity = TestUserRoleStub.getStubbedUserRoleEntity();

		when(userRoleRepository.findByApplicationIdAndStatus(entity.getApplicationId(), StatusType.ACTIVE.getName()))
				.thenReturn(null);

		userRoleService.fetchUserRoleForWF(entity.getWorkflowSequenceIds().get(0), entity.getApplicationId());
	}

	@Test
	public void test_fetchUserRoleForWF_successEmptyUserRoleEntityList() throws Exception {
		// shadab
		UserRoleEntity entity = TestUserRoleStub.getStubbedUserRoleEntity();
		List<UserRoleEntity> entityList = new ArrayList<UserRoleEntity>();

		when(userRoleRepository.findByApplicationIdAndStatus(entity.getApplicationId(), StatusType.ACTIVE.getName()))
				.thenReturn(entityList);

		userRoleService.fetchUserRoleForWF(entity.getWorkflowSequenceIds().get(0), entity.getApplicationId());
	}

	@Test
	public void test_fetchUserRoleForWF_successA() throws Exception {
		// shadab
		UserRoleEntity entity = TestUserRoleStub.getStubbedUserRoleEntity();
		List<UserRoleEntity> entityList = new ArrayList<UserRoleEntity>();
		entityList.add(entity);

		when(userRoleRepository.findByApplicationIdAndStatus(entity.getApplicationId(), StatusType.ACTIVE.getName()))
				.thenReturn(entityList);

		userRoleService.fetchUserRoleForWF("Not WorkflowSeq", entity.getApplicationId());
	}

}
