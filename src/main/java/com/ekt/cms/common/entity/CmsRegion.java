package com.ekt.cms.common.entity;

/**
 * 2016-05-07
 * 
 * @author zhuyanqiong
 */

public class CmsRegion {
	private String code;
	
	private String province;// 省
	
	private String city;// 市
	
	private String area;// 区 县
	
	private int status;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

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

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
