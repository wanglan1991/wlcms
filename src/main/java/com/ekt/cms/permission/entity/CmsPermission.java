package com.ekt.cms.permission.entity;

import java.sql.Date;

public class CmsPermission {
	private Integer roleId;

	private Integer PermissionId;

	private String roleName;

	private String PermissionName;

	private String encoding;

	private String key;

	private Date roleCreateTime;

	private int roleStatus;
	
	private Integer order;
	
	private String value;

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getPermissionName() {
		return PermissionName;
	}

	public void setPermissionName(String permissionName) {
		PermissionName = permissionName;
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public Date getRoleCreateTime() {
		return roleCreateTime;
	}

	public void setRoleCreateTime(Date roleCreateTime) {
		this.roleCreateTime = roleCreateTime;
	}

	public int getRoleStatus() {
		return roleStatus;
	}

	public void setRoleStatus(int roleStatus) {
		this.roleStatus = roleStatus;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getPermissionId() {
		return PermissionId;
	}

	public void setPermissionId(Integer permissionId) {
		PermissionId = permissionId;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	
}