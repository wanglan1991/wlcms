package com.ekt.cms.report.entity;

import java.util.Date;
/**
 * 
* @Title: UserActiveReport.java 
* @Package com.ekt.cms.report.entity 
* @Description: TODO(统计用户活跃度实体类) 
* @author wanglan
* @date 2017年3月30日 下午3:55:08 
* @version V1.0
 */

public class UserActiveReport {
	
	private int id;//主键
	private Date date;// 日期
	private int userCount;// 总用户 人
	private int increaseUserCount;// 新增用户 人
	private int loginUserCount;	// 登录用户 人
	private int loginTimes;// 登录总 次
	private int webLoginTimes;// web端登录 次
	private int appLoginTimes;// app端登录 次
	private int payUserCount;// 购买 人
	private int payTimes;// 购买 次
	private int videoUseUserCount;// 视频点播 人
	private int videoUseTimes;// 视频点播 次
	private int exerciseUseUserCount;// 习题使用 人
	private int newsUseUserCount;// 动态使用 人
	private int messageUseUserCount;// 消息中心使用 人
	private int dayOrMonthOrYear;
	
	public int getDayOrMonthOrYear() {
		return dayOrMonthOrYear;
	}
	public void setDayOrMonthOrYear(int dayOrMonthOrYear) {
		this.dayOrMonthOrYear = dayOrMonthOrYear;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getUserCount() {
		return userCount;
	}
	public void setUserCount(int userCount) {
		this.userCount = userCount;
	}
	public int getIncreaseUserCount() {
		return increaseUserCount;
	}
	public void setIncreaseUserCount(int increaseUserCount) {
		this.increaseUserCount = increaseUserCount;
	}
	public int getLoginUserCount() {
		return loginUserCount;
	}
	public void setLoginUserCount(int loginUserCount) {
		this.loginUserCount = loginUserCount;
	}
	public int getLoginTimes() {
		return loginTimes;
	}
	public void setLoginTimes(int loginTimes) {
		this.loginTimes = loginTimes;
	}
	public int getWebLoginTimes() {
		return webLoginTimes;
	}
	public void setWebLoginTimes(int webLoginTimes) {
		this.webLoginTimes = webLoginTimes;
	}
	public int getAppLoginTimes() {
		return appLoginTimes;
	}
	public void setAppLoginTimes(int appLoginTimes) {
		this.appLoginTimes = appLoginTimes;
	}
	public int getPayUserCount() {
		return payUserCount;
	}
	public void setPayUserCount(int payUserCount) {
		this.payUserCount = payUserCount;
	}
	public int getPayTimes() {
		return payTimes;
	}
	public void setPayTimes(int payTimes) {
		this.payTimes = payTimes;
	}
	public int getVideoUseUserCount() {
		return videoUseUserCount;
	}
	public void setVideoUseUserCount(int videoUseUserCount) {
		this.videoUseUserCount = videoUseUserCount;
	}
	public int getVideoUseTimes() {
		return videoUseTimes;
	}
	public void setVideoUseTimes(int videoUseTimes) {
		this.videoUseTimes = videoUseTimes;
	}
	public int getExerciseUseUserCount() {
		return exerciseUseUserCount;
	}
	public void setExerciseUseUserCount(int exerciseUseUserCount) {
		this.exerciseUseUserCount = exerciseUseUserCount;
	}
	public int getNewsUseUserCount() {
		return newsUseUserCount;
	}
	public void setNewsUseUserCount(int newsUseUserCount) {
		this.newsUseUserCount = newsUseUserCount;
	}
	public int getMessageUseUserCount() {
		return messageUseUserCount;
	}
	public void setMessageUseUserCount(int messageUseUserCount) {
		this.messageUseUserCount = messageUseUserCount;
	}
	
}
