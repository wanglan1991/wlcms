package com.ekt.cms.common.dao;

public interface CmsTimingTaskMapper {
	/**
	 * 插入昨日整日的用户活跃度统计相关数据
	 * @return
	 */
	public int insertTodayActiveReportData();
	
	/**
	 * 更新交易支付未完成且交易时间过期的优惠劵状态为可用 use_status=0
	 * @return
	 */
	public int updateCouponsStatus();
}
