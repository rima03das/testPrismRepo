package com.cee.userrole.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.cee.userrole.entities.ApplicationDashboard;
import com.cee.userrole.entities.UserRoleEntity;
import com.cee.userrole.exception.BadRequestException;
import com.cee.userrole.models.UserRoleModel;
import com.cee.userrole.repositories.UserRoleRepository;
import com.cee.userrole.util.StatusType;
import com.cee.userrole.util.constants.AppConstant;

@Service
public class UserRoleService {

	static final String ROLE_ID_BLANK = "Role Id can not be empty";
	static final String APPLICATION_ID_BLANK = "Application Id can not be empty";

	@Autowired
	private UserRoleRepository userRoleRepository;

	private final MongoTemplate mongoTemplate;

	@Autowired
	public UserRoleService(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	public UserRoleEntity validateAndCreateModel(final UserRoleModel model, String appId) throws BadRequestException {

		// Validate

		if (StringUtils.isBlank(model.getApplicationId())) {
			throw new BadRequestException(APPLICATION_ID_BLANK);
		}

		if (StringUtils.isBlank(model.getRole())) {
			throw new BadRequestException("Role can not be empty");
		}

		if (model.getWorkflowSequenceIds().isEmpty()) {
			throw new BadRequestException("Workflow sequence ids can not be empty");
		}

		final UserRoleEntity entity = createEntity(model);

		return this.saveUserRole(entity, appId);
	}

	public UserRoleEntity validateAndUpdateModel(final UserRoleModel model, String appId) throws BadRequestException {

		// Validate

		if (StringUtils.isBlank(model.getApplicationId())) {
			throw new BadRequestException(APPLICATION_ID_BLANK);
		}

		if (StringUtils.isBlank(model.getRole())) {
			throw new BadRequestException("Role can not be empty");
		}

		if (StringUtils.isBlank(model.getId())) {
			throw new BadRequestException(ROLE_ID_BLANK);
		}

		final UserRoleEntity entity = createEntity(model);

		return this.saveUserRole(entity, appId);
	}

	private UserRoleEntity createEntity(final UserRoleModel model) {
		// populate entity
		final UserRoleEntity entity = new UserRoleEntity();
		entity.setId(model.getId());
		entity.setApplicationId(model.getApplicationId());
		entity.setStatus(StatusType.ACTIVE.getName());
		entity.setRole(model.getRole());
		entity.setWorkflowSequenceIds(model.getWorkflowSequenceIds());
		return entity;
	}

	public UserRoleEntity saveUserRole(final UserRoleEntity userRoleEntity, String applicaitonid) {

		// Query With active role and update if exist
		Optional<UserRoleEntity> roleOptional = userRoleRepository.findByApplicatioIdAndRoleAndStatus(
				userRoleEntity.getApplicationId(), userRoleEntity.getRole(), StatusType.ACTIVE.name());
		if (roleOptional.isPresent()) {
			UserRoleEntity dbRole = roleOptional.get();
			userRoleEntity.setId(dbRole.getId());
		}
		// save
		UserRoleEntity entity = userRoleRepository.save(userRoleEntity);
		/** codes for dashboard count starts **/
		long userRoleCount = userRoleRepository.countByApplicationIdAndStatus(applicaitonid,
				StatusType.ACTIVE.getName());
		Query query = new Query(Criteria.where(AppConstant.APP_ID_FIELD).is(applicaitonid));
		Update update = new Update().set(AppConstant.USER_ROLE_COUNT_FIELD, userRoleCount);
		mongoTemplate.upsert(query, update, ApplicationDashboard.class);
		/** codes for dashboard count endss **/
		return entity;
	}

	public List<UserRoleEntity> fetchUserRoleByApplId(final String applId) throws BadRequestException {

		if (StringUtils.isBlank(applId)) {
			throw new BadRequestException(APPLICATION_ID_BLANK);
		}

		// Query
		return userRoleRepository.findByApplicationIdAndStatusOrderByAuditingEntitiesUpdateDateDesc(applId,
				StatusType.ACTIVE.name());

	}

	public UserRoleEntity fetchUserRoleByRoleId(final String roleId) throws BadRequestException {

		if (StringUtils.isBlank(roleId)) {
			throw new BadRequestException(ROLE_ID_BLANK);
		}

		// Query
		Optional<UserRoleEntity> roleOptional = userRoleRepository.findByIdAndStatus(roleId, StatusType.ACTIVE.name());
		if (!roleOptional.isPresent()) {
			throw new BadRequestException("Please specify correct role id");
		}
		return roleOptional.get();

	}

	public UserRoleEntity deleteUserRoleByRoleId(String roleId, String appId) throws BadRequestException {

		if (StringUtils.isBlank(roleId)) {
			throw new BadRequestException(ROLE_ID_BLANK);
		}

		// Query
		Optional<UserRoleEntity> roleOptional = userRoleRepository.findByIdAndStatus(roleId, StatusType.ACTIVE.name());
		if (!roleOptional.isPresent()) {
			throw new BadRequestException("Please specify correct role id");
		}

		UserRoleEntity role = roleOptional.get();
		role.setStatus(StatusType.INACTIVE.name());

		return this.saveUserRole(role, appId);

	}

	public UserRoleEntity updateRoleFromWorkflow(List<String> model, String workflowSeq, String applId) {

		UserRoleEntity roleEntity = null;
		for (String role : model) {
			Optional<UserRoleEntity> roleExist = userRoleRepository.findByIdAndStatus(role,
					StatusType.ACTIVE.getName());
			if (roleExist.isPresent()) {
				roleEntity = new UserRoleEntity();

				roleEntity.setApplicationId(applId);
				roleEntity.setId(roleExist.get().getId());
				roleEntity.setRole(roleExist.get().getRole());
				roleEntity.setStatus(StatusType.ACTIVE.getName());

				List<String> workfkows = roleExist.get().getWorkflowSequenceIds();
				if (!workfkows.contains(workflowSeq)) {
					workfkows.add(workflowSeq);
					roleEntity.setWorkflowSequenceIds(workfkows);
				}

				userRoleRepository.save(roleEntity);

				/** codes for dashboard count starts **/
				long userRoleCount = userRoleRepository.countByApplicationIdAndStatus(applId,
						StatusType.ACTIVE.getName());
				Query query = new Query(Criteria.where(AppConstant.APP_ID_FIELD).is(applId));
				Update update = new Update().set(AppConstant.USER_ROLE_COUNT_FIELD, userRoleCount);
				mongoTemplate.upsert(query, update, ApplicationDashboard.class);
				/** codes for dashboard count endss **/
			}
		}

		List<UserRoleEntity> roleExist = userRoleRepository.findByApplicationIdAndStatus(applId,
				StatusType.ACTIVE.getName());
		if (roleExist != null) {
			for (UserRoleEntity entity : roleExist) {
				if (!model.contains(entity.getId()) && entity.getWorkflowSequenceIds().contains(workflowSeq)) {
					entity.getWorkflowSequenceIds().remove(workflowSeq);
					userRoleRepository.save(entity);

					userRoleRepository.save(roleEntity);

					/** codes for dashboard count starts **/
					long userRoleCount = userRoleRepository.countByApplicationIdAndStatus(applId,
							StatusType.ACTIVE.getName());
					Query query = new Query(Criteria.where(AppConstant.APP_ID_FIELD).is(applId));
					Update update = new Update().set(AppConstant.USER_ROLE_COUNT_FIELD, userRoleCount);
					mongoTemplate.upsert(query, update, ApplicationDashboard.class);
					/** codes for dashboard count endss **/
				}
			}
		}

		return roleEntity;
	}

	public List<String> fetchUserRoleForWF(String workflowSeq, String applId) {

		List<String> roleIds = new ArrayList<>();
		List<UserRoleEntity> roleExist = userRoleRepository.findByApplicationIdAndStatus(applId,
				StatusType.ACTIVE.getName());
		if (roleExist != null && roleExist.size() > 0) {
			for (UserRoleEntity user : roleExist) {
				if (user.getWorkflowSequenceIds().contains(workflowSeq)) {
					roleIds.add(user.getId());
				}
			}
		}
		return roleIds;
	}
}
