package com.ekt.cms.account.service;

import java.util.ArrayList;

import com.ekt.cms.account.entity.CmsLoginRecord;

/**
 * 
* @Title: ICmsLoginRecordService.java 
* @Package com.ekt.cms.account.service 
* @Description: TODO(cms登录日志记录) 
* @author wanglan
* @date 2017年5月20日 下午4:22:14 
* @version V1.0
 */
public interface ICmsLoginRecordService {
	
	/**
	 * 获取登录列表详情
	 * @param cr
	 * @return
	 */
	public ArrayList<CmsLoginRecord> getCmsLoginRecordList(CmsLoginRecord cr);
	/**
	 * 插入登录记录
	 * @param cr
	 * @return
	 */
	public int insertLoginRecord(CmsLoginRecord cr);

}
