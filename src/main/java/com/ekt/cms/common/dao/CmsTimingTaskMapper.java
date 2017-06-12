package com.ekt.cms.common.dao;

public interface CmsTimingTaskMapper {
	/**
	 * 插入昨日整日的用户活跃度统计相关数据
	 * @return
	 */
	public int insertTodayActiveReportData();
}
