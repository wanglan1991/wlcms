package com.ekt.cms.report.service;

import java.util.List;

import com.ekt.cms.report.entity.UserActiveReport;
import com.ekt.cms.report.entity.UserActiveReportValidBean;
/**
 * 
* @Title: ICmsUserActiveReportService.java 
* @Package com.ekt.cms.report.service 
* @Description: TODO(统计用户活跃度接口) 
* @author wanglan
* @date 2017年3月30日 下午4:09:01 
* @version V1.0
 */
public interface ICmsUserActiveReportService {
	
	/**
	 * 根据 UserActiveReportValidBean 入参校验bean 获取list
	 * @param page
	 * @param uarvb
	 * @return
	 */
	public  List<UserActiveReport> getList(UserActiveReportValidBean uarvb);

}
