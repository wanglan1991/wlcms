package com.ekt.cms.account.dao;

import java.util.ArrayList;

import com.ekt.cms.account.entity.CmsLoginRecord;

public interface CmsLoginRecordMapper {

	
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
