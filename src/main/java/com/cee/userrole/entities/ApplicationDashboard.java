package com.cee.userrole.entities;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.cee.userrole.models.BaseEntity;
import com.cee.userrole.util.StatusType;


@Document(collection = "v2_dashboard_count")
public class ApplicationDashboard extends BaseEntity{
	
	@Field("application_id")
	private String applicationId;	

	@Field("userrole_count")
	private long userRoleCount;

	private String status = StatusType.ACTIVE.getName();
	
	public ApplicationDashboard(long userRoleCount, String status) {
		super();
		this.userRoleCount = userRoleCount;
		this.status = status;
	}

	public String getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	public long getuserRoleCount() {
		return userRoleCount;
	}

	public void setuserRoleCount(long userRoleCount) {
		this.userRoleCount = userRoleCount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
