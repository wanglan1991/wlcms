package com.ekt.cms.agency.entity;

import java.util.Date;

public class CmsAgency {
	
	private int id;
	private int userId; 
	private String agencyName; 
	private String telephone;
	private Date createTime; 
	private int parentId; 
	private String parentAgencyName;
	private int agencyCount;
	private double salesVolume; 
	private double childSalesVolume;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getAgencyName() {
		return agencyName;
	}
	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
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
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public String getParentAgencyName() {
		return parentAgencyName;
	}
	public void setParentAgencyName(String parentAgencyName) {
		this.parentAgencyName = parentAgencyName;
	}
	
	public int getAgencyCount() {
		return agencyCount;
	}
	public void setAgencyCount(int agencyCount) {
		this.agencyCount = agencyCount;
	}
	public double getSalesVolume() {
		return salesVolume;
	}
	public void setSalesVolume(double salesVolume) {
		this.salesVolume = salesVolume;
	}
	public double getChildSalesVolume() {
		return childSalesVolume;
	}
	public void setChildSalesVolume(double childSalesVolume) {
		this.childSalesVolume = childSalesVolume;
	}

}
