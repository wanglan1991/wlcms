package com.ekt.cms.common.service;

public interface ICmsTimingTaskService {
	
	/**
	 * 插入昨日整日的用户活跃度统计相关数据
	 * @return
	 */
	public int insertTodayActiveReportData();
	
	/**
	 * 交易失效后 归还该交易中使用的优惠劵
	 * @return
	 */
	public int returnCoupons();

}
