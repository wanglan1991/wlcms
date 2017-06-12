package com.ekt.cms.report.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ekt.cms.report.entity.UserActiveReport;
import com.ekt.cms.report.entity.UserActiveReportValidBean;

/**
 * 
* @Title: CmsUserActiveReportMapper.java 
* @Package com.ekt.cms.report.dao 
* @Description: TODO(什么都不想说 你懂的) 
* @author wanglan
* @date 2017年3月30日 下午4:12:52 
* @version V1.0
 */
public interface CmsUserActiveReportMapper {
	
	/**
	 * 根据 UserActiveReportValidBean 入参校验bean 获取list
	 * @param page
	 * @param uarvb
	 * @return
	 */
	public  List<UserActiveReport> getList(@Param("uarvb")UserActiveReportValidBean uarvb);

}
