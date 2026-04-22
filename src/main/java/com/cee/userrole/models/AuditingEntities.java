package com.cee.userrole.models;

import java.util.Date;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
public class AuditingEntities {
	@CreatedBy
	@Field("created_by")
	protected String createdBy;
	@CreatedDate
	@Field("create_date")
	protected Date createDate;
	@LastModifiedBy
	@Field("updated_by")
	protected String updatedBy;
	@LastModifiedDate
	@Field("update_date")
	protected Date updateDate;

	public AuditingEntities() {
		super();
	}
	/**
	 * @return the createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}
	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	/**
	 * @return the createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}
	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	/**
	 * @return the updatedBy
	 */
	public String getUpdatedBy() {
		return updatedBy;
	}
	/**
	 * @param updatedBy the updatedBy to set
	 */
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	/**
	 * @return the updateDate
	 */
	public Date getUpdateDate() {
		return updateDate;
	}
	/**
	 * @param updateDate the updateDate to set
	 */
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	@Override
	public String toString() {
		return "AuditingEntities [createdBy=" + createdBy + ", createDate=" + createDate + ", updatedBy=" + updatedBy
				+ ", upcateDate=" + updateDate + ", getCreatedBy()=" + getCreatedBy() + ", getCreateDate()="
				+ getCreateDate() + ", getUpdatedBy()=" + getUpdatedBy() + ", getUpcateDate()=" + getUpdateDate()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}
	

}
