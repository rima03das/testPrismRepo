package com.cee.userrole.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.data.mongodb.core.mapping.Field;

public class BaseEntity {

	@JsonIgnore
	@Field("auditing_entities")
	private AuditingEntities auditingEntities = new AuditingEntities();

	public AuditingEntities getAuditingEntities() {
		return auditingEntities;
	}

	public void setAuditingEntities(AuditingEntities auditingEntities) {
		this.auditingEntities = auditingEntities;
	}
}
