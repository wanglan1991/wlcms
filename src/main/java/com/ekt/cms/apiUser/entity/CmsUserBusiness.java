package com.ekt.cms.apiUser.entity;

import java.util.Date;

public class CmsUserBusiness {
	
	private int businessId;//事务ID
	private int userId;//用户ID
	private String realname;//用户真实姓名
	private String username;//用户名
	private String telephone;//手机号码
	private Date createTime;//提交时间
	private String remark ;//备注
	private String businessType;//事物类型
	private Integer acceptanceStatus;//办理状态
	private int acceptanceAccountId;//处理用cms账户id
	private String acceptanceContent;//受理内容
	private Date acceptanceTime;//受理时间
	private String accountRealname;//受理人真实姓名
	
	public String getAccountRealname() {
		return accountRealname;
	}
	public void setAccountRealname(String accountRealname) {
		this.accountRealname = accountRealname;
	}
	public Date getAcceptanceTime() {
		return acceptanceTime;
	}
	public void setAcceptanceTime(Date acceptanceTime) {
		this.acceptanceTime = acceptanceTime;
	}
	public int getBusinessId() {
		return businessId;
	}
	public void setBusinessId(int businessId) {
		this.businessId = businessId;
	}
	public int getAcceptanceAccountId() {
		return acceptanceAccountId;
	}
	public void setAcceptanceAccountId(int acceptanceAccountId) {
		this.acceptanceAccountId = acceptanceAccountId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getBusinessType() {
		return businessType;
	}
	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}
	public Integer getAcceptanceStatus() {
		return acceptanceStatus;
	}
	public void setAcceptanceStatus(Integer acceptanceStatus) {
		this.acceptanceStatus = acceptanceStatus;
	}
	public String getAcceptanceContent() {
		return acceptanceContent;
	}
	public void setAcceptanceContent(String acceptanceContent) {
		this.acceptanceContent = acceptanceContent;
	}
	
}
