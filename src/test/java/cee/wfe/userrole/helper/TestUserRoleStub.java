package cee.wfe.userrole.helper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cee.userrole.entities.UserRoleEntity;
import com.cee.userrole.models.AuditingEntities;
import com.cee.userrole.models.UserRoleModel;

public class TestUserRoleStub {

	public static UserRoleModel getStubbedUserRoleModel() {
		UserRoleModel model = new UserRoleModel();
		model.setApplicationId("CEE_TEST");
		model.setId("id");
		model.setRole("role");

		List<String> list = new ArrayList<String>();
		list.add("workflowSeq");
		model.setWorkflowSequenceIds(list);
		return model;
	}

	public static UserRoleEntity getStubbedUserRoleEntity() {
		UserRoleEntity entity = new UserRoleEntity();
		entity.setApplicationId("CEE_TEST");

		AuditingEntities entities = new AuditingEntities();
		entities.setCreateDate(new Date());
		entities.setCreatedBy("createdBy");
		entities.setUpdateDate(new Date());
		entities.setUpdatedBy("updatedBy");
		entity.setAuditingEntities(entities);
		entity.setId("id");
		entity.setRole("role");
		entity.setStatus("ACTIVE");

		List<String> list = new ArrayList<String>();
		list.add("1");
		entity.setWorkflowSequenceIds(list);
		return entity;
	}

	public static UserRoleModel getStubbedUserRoleModelBlankApplicationId() {
		UserRoleModel model = new UserRoleModel();
		model.setApplicationId(" ");
		model.setId("id");
		model.setRole("role");

		List<String> list = new ArrayList<String>();
		list.add("workflowSeq");
		model.setWorkflowSequenceIds(list);
		return model;
	}

	public static UserRoleModel getStubbedUserRoleModelBlankRole() {
		UserRoleModel model = new UserRoleModel();
		model.setApplicationId("CEE_TEST");
		model.setId("id");
		model.setRole(" ");

		List<String> list = new ArrayList<String>();
		list.add("workflowSeq");
		model.setWorkflowSequenceIds(list);
		return model;
	}

	public static UserRoleModel getStubbedUserRoleModelBlankWorkflowSequenceIds() {
		UserRoleModel model = new UserRoleModel();
		model.setApplicationId("CEE_TEST");
		model.setId("id");
		model.setRole("role");

		List<String> list = new ArrayList<String>();
		model.setWorkflowSequenceIds(list);
		return model;
	}

	public static UserRoleModel getStubbedUserRoleModelBlankId() {
		UserRoleModel model = new UserRoleModel();
		model.setApplicationId("CEE_TEST");
		model.setId(" ");
		model.setRole("role");

		List<String> list = new ArrayList<String>();
		list.add("workflowSeq");
		model.setWorkflowSequenceIds(list);
		return model;
	}

	public static UserRoleEntity getStubbedUserRoleEntityNotContainsWorkflowSeq() {
		UserRoleEntity entity = new UserRoleEntity();
		entity.setApplicationId("CEE_TEST");

		AuditingEntities entities = new AuditingEntities();
		entities.setCreateDate(new Date());
		entities.setCreatedBy("createdBy");
		entities.setUpdateDate(new Date());
		entities.setUpdatedBy("updatedBy");
		entity.setAuditingEntities(entities);
		entity.setId("id");
		entity.setRole("role");
		entity.setStatus("ACTIVE");

		List<String> list = new ArrayList<String>();
		entity.setWorkflowSequenceIds(list);
		return entity;
	}
}
