package com.cee.userrole.controller;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cee.userrole.entities.UserRoleEntity;
import com.cee.userrole.entities.View;
import com.cee.userrole.exception.BadRequestException;
import com.cee.userrole.messages.GenericMessageBuilder;
import com.cee.userrole.messages.GenericResponseMessage;
import com.cee.userrole.models.UserRoleModel;
import com.cee.userrole.services.UserRoleService;
import com.fasterxml.jackson.annotation.JsonView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/api/v2/application/{applicationId}/user-role")
public class UserRoleController {

	@Autowired
	UserRoleService userRoleService;

	public UserRoleController(UserRoleService userRoleService) {
		this.userRoleService = userRoleService;
	}

	/**
	 * Create user role
	 * 
	 * @param model
	 * @param token
	 * @param request
	 * @return
	 * @throws BadRequestException
	 */
	@PostMapping(value = "/")
	@JsonView(value = View.UserRoleView.Query.class)
	public ResponseEntity<GenericResponseMessage<UserRoleEntity>> createUserRole(
			@Valid @RequestBody UserRoleModel model, @PathVariable(name = "applicationId") String applId,
			@RequestHeader(name = "token") String token, HttpServletRequest request) throws BadRequestException {

		GenericMessageBuilder<UserRoleEntity> genericMessageBuilder = new GenericMessageBuilder<>();
		UserRoleEntity urEntity = userRoleService.validateAndCreateModel(model, applId);
		List<UserRoleEntity> roles = Arrays.asList(urEntity);
		return genericMessageBuilder.setResult(roles).buildResponseEntity(HttpStatus.OK);
	}

	/**
	 * Update user role
	 * 
	 * @param model
	 * @param token
	 * @param request
	 * @return
	 * @throws BadRequestException
	 */
	@PatchMapping(value = "/")
	@JsonView(value = View.UserRoleView.Query.class)
	public ResponseEntity<GenericResponseMessage<UserRoleEntity>> updateUserRole(
			@Valid @RequestBody UserRoleModel model, @PathVariable(name = "applicationId") String applId,
			@RequestHeader(name = "token") String token, HttpServletRequest request) throws BadRequestException {

		GenericMessageBuilder<UserRoleEntity> genericMessageBuilder = new GenericMessageBuilder<>();
		UserRoleEntity urEntity = userRoleService.validateAndUpdateModel(model, applId);
		List<UserRoleEntity> roles = Arrays.asList(urEntity);
		return genericMessageBuilder.setResult(roles).buildResponseEntity(HttpStatus.OK);

	}

	/**
	 * Get user role by applicationId or roleId
	 */
	@GetMapping(value = "/")
	@JsonView(value = View.UserRoleView.Query.class)
	public ResponseEntity<GenericResponseMessage<UserRoleEntity>> fetchUserRoles(
			@PathVariable(name = "applicationId") String applId,
			@RequestParam(name = "roleId", required = false) String roleId, @RequestHeader(name = "token") String token,
			HttpServletRequest request) throws BadRequestException {

		GenericMessageBuilder<UserRoleEntity> genericMessageBuilder = new GenericMessageBuilder<>();
		List<UserRoleEntity> roles;

		if (StringUtils.isNotBlank(roleId)) {
			UserRoleEntity role = userRoleService.fetchUserRoleByRoleId(roleId);
			roles = Arrays.asList(role);
		} else {
			roles = userRoleService.fetchUserRoleByApplId(applId);
		}
		return genericMessageBuilder.setResult(roles).buildResponseEntity(HttpStatus.OK);
	}

	/**
	 * Delete role by role Id
	 */
	@DeleteMapping(value = "/")
	@JsonView(value = View.UserRoleView.Query.class)
	public ResponseEntity<GenericResponseMessage<UserRoleEntity>> deleteUserRoles(
			@PathVariable(name = "applicationId") String applId, @Valid @RequestParam("roleId") String roleId,
			@RequestHeader(name = "token") String token, HttpServletRequest request) throws BadRequestException {

		GenericMessageBuilder<UserRoleEntity> genericMessageBuilder = new GenericMessageBuilder<>();
		UserRoleEntity role = userRoleService.deleteUserRoleByRoleId(roleId, applId);
		List<UserRoleEntity> roles = Arrays.asList(role);
		return genericMessageBuilder.setResult(roles).buildResponseEntity(HttpStatus.OK);
	}

	/**
	 * Get user role by applicationId or roleId
	 */
	@PostMapping(value = "/update/{workflowSeq}")
	@JsonView(value = View.UserRoleView.Query.class)
	public ResponseEntity<GenericResponseMessage<UserRoleEntity>> saveUserRoles(@RequestBody List<String> model,
			@PathVariable(name = "workflowSeq") String workflowSeq, @PathVariable(name = "applicationId") String applId,
			@RequestHeader(name = "token") String token, HttpServletRequest request) throws BadRequestException {

		GenericMessageBuilder<UserRoleEntity> genericMessageBuilder = new GenericMessageBuilder<>();

		UserRoleEntity urEntity = userRoleService.updateRoleFromWorkflow(model, workflowSeq, applId);
		List<UserRoleEntity> roles = Arrays.asList(urEntity);
		return genericMessageBuilder.setResult(roles).buildResponseEntity(HttpStatus.OK);
	}

	@GetMapping(value = "/fetch/{workflowSeq}")
	@JsonView(value = View.UserRoleView.Query.class)
	public ResponseEntity<GenericResponseMessage<String>> fetchUserRoleForWF(
			@PathVariable(name = "workflowSeq") String workflowSeq, @PathVariable(name = "applicationId") String applId,
			@RequestHeader(name = "token") String token, HttpServletRequest request) throws BadRequestException {
		GenericMessageBuilder<String> genericMessageBuilder = new GenericMessageBuilder<>();
		List<String> urEntity = userRoleService.fetchUserRoleForWF(workflowSeq, applId);
		return genericMessageBuilder.setResult(urEntity).buildResponseEntity(HttpStatus.OK);
	}
}