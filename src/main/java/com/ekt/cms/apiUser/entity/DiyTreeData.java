package com.ekt.cms.apiUser.entity;


public class DiyTreeData {
	
	private int id;//permissionId
	private String expireTime;//过期时间
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getExpireTime() {
		return expireTime;
	}
	public void setExpireTime(String expireTime) {
		this.expireTime = expireTime+" 00:00:00";
	}
	
	
	

}
