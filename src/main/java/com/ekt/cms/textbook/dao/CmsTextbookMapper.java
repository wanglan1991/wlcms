package com.ekt.cms.textbook.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ekt.cms.textbook.entity.CmsCatalogMessage;
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
	
	/**
	 * 根据textbookId获取该教材的所有节
	 * @param textbookId
	 * @return
	 */
	public List<Map<String, Object>> getCatalogTree(int textbookId);
	

	/**
	 * 根据catalogId获取catalog以及视频相关信息
	 * @param list
	 * @return
	 */
	public List<CmsCatalogMessage> selectCatalogById(@Param("list")String [] list);

}
