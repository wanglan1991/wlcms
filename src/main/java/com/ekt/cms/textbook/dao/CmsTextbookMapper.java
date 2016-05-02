package com.ekt.cms.textbook.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ekt.cms.textbook.entity.CmsTextbook;

/**
 * 
 * @author wanglan
 *2016-05-02
 *教材DAO
 */
public interface CmsTextbookMapper {
	/**
	 * 获取textbook集合
	 * @param cmsTextbook
	 * @return
	 */
	public List<CmsTextbook> listPage(@Param("textbook") CmsTextbook cmsTextbook);

}
