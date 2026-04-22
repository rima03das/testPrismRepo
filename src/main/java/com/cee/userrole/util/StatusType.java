package com.cee.userrole.util;

public enum StatusType {

	INACTIVE("INACTIVE", "Record Is inactive"),
	ACTIVE("ACTIVE", "Record Is Active");
	
	private String name;
	private String desc;

	private StatusType(String name, String desc) {
		this.name = name;
		this.desc = desc;
	}

	/**
	 * @return the name
	 */
	public final String getName() {
		return name;
	}

	/**
	 * @return the desc
	 */
	public final String getDesc() {
		return desc;
	}
}
