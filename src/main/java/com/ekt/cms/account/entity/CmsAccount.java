package com.ekt.cms.account.entity;

import java.util.List;

import org.hibernate.validator.constraints.NotBlank;

import com.ekt.cms.permission.entity.CmsPermission;

public class CmsAccount {
	private Integer id;
	@NotBlank(message = "账号不能为空")
	private String userName;
	@NotBlank(message = "密码不能为空")
	private String password;

	private String realName;

	private Integer role;

	private String cellphone;

	private Integer status;

	private boolean rememberMe;

	private List<CmsPermission> cmsPermissions;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public Integer getRole() {
		return role;
	}

	public void setRole(Integer role) {
		this.role = role;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public boolean isRememberMe() {
		return rememberMe;
	}

	public void setRememberMe(boolean rememberMe) {
		this.rememberMe = rememberMe;
	}

	public List<CmsPermission> getCmsPermissions() {
		return cmsPermissions;
	}

	public void setCmsPermissions(List<CmsPermission> cmsPermissions) {
		this.cmsPermissions = cmsPermissions;
	}
}

