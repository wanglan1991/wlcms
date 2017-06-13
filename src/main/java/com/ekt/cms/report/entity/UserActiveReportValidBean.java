package com.ekt.cms.report.entity;
/**
 * 
* @Title: UserActiveReportValidBean.java 
* @Package com.ekt.cms.report.entity 
* @Description: TODO(用户统计入参过滤bean) 
* @author wanglan
* @date 2017年3月30日 下午4:07:54 
* @version V1.0
 */
public class UserActiveReportValidBean {
	
	
	private int dayOrMonthOrYear = 1;//默认按照日 1月，2日，3年

	public int getDayOrMonthOrYear() {
		return dayOrMonthOrYear;
	}

	public void setDayOrMonthOrYear(int dayOrMonthOrYear) {
		this.dayOrMonthOrYear = dayOrMonthOrYear;
	}
	
	

}
