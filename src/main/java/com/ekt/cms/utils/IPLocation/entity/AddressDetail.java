package com.ekt.cms.utils.IPLocation.entity;

/**
 * 地址详细信息
 * 王岚
 * @author wanglan
 * 2017年3月28日下午8:08:03
 */
public class AddressDetail {
	
	private String province;//省
	private String city;//城市
	private String district;//区
	private String street;//街道
	private String street_number;//街道编号
	private int city_code;//城市编号
	
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getStreet_number() {
		return street_number;
	}
	public void setStreet_number(String street_number) {
		this.street_number = street_number;
	}
	public int getCity_code() {
		return city_code;
	}
	public void setCity_code(int city_code) {
		this.city_code = city_code;
	}
	
}
