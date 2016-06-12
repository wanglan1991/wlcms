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

	
	/**
	 * 插入新教材
	 * @param cmsTextbook
	 * @return
	 */
	public Integer insertTextbook(CmsTextbook cmsTextbook);
	/**
	 * 根据id删除教材
	 * @param id
	 * @return
	 */
	public Integer deleteTextbook(@Param("id")Integer id);
	/**
	 * 停启用教材
	 * @param id
	 * @param status
	 * @return
	 */
	public Integer confine(@Param("id")Integer id, @Param("status")Integer status);
	
	/**
	 * 修改教材
	 * @param cmsTextbook
	 * @return
	 */
	public Integer updateTextbook(CmsTextbook cmsTextbook);

}
