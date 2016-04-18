package com.ekt.cms.index.entity;

import org.hibernate.validator.constraints.NotBlank;
/**
 * 登录用户
 * @author 王岚
 *
 */
public class User {
	
	
	@NotBlank(message="用户名不能为空")
	private String userName;
	
	@NotBlank(message="用户名不能为空")
	private String password;
	
	
	private boolean rememberMe;
	
	public boolean isRememberMe() {
		return rememberMe;
	}

	public void setRememberMe(boolean rememberMe) {
		this.rememberMe = rememberMe;
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


}
