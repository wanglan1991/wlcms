package com.ekt.cms.utils.IPLocation.entity;

/**
 * 
 * @author wanglan
 * 2017年3月28日下午8:15:19
 */
public class Content {
	
	private AddressDetail address_detail;
	private String address;
	private Point point;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
		this.point = point;
	}

	public AddressDetail getAddress_detail() {
		return address_detail;
	}

	public void setAddress_detail(AddressDetail address_detail) {
		this.address_detail = address_detail;
	}

}
