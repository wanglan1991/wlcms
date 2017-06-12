package com.ekt.cms.account.entity;

import java.util.Date;

/**   
* @Title: CmsLoginRecord.java 
* @Package com.ekt.cms.account.entity 
* @Description: TODO(登录日志对象) 
* @author wanglan
* @date 2017年5月20日 下午4:23:34 
* @version V1.0   
*/
public class CmsLoginRecord {
	
	private int id;
	private int accountId;
	private String accountName;
	private String cellphone;
	private String realName;
	private String roleName;
	private Date loginTime;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getCellphone() {
		return cellphone;
	}
	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public Date getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	


}
