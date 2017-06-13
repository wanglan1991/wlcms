package com.ekt.cms.utils.IPLocation.entity;
/**
 * 
 * @author wanglan
 * 2017年3月28日下午8:10:27
 */
public class IPInfo {
	private String address ;
	private Content content;
	private int status;
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Content getContent() {
		return content;
	}
	public void setContent(Content content) {
		this.content = content;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	
}
