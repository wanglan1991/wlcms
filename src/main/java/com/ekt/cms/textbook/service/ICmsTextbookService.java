package com.ekt.cms.textbook.service;

import java.util.List;
import java.util.Map;

import com.ekt.cms.textbook.entity.CmsCatalogMessage;
import com.ekt.cms.textbook.entity.CmsTextbook;

/**
 * 
 * @author wanglan 2016-05-02
 */
public interface ICmsTextbookService {
	/**
	 * 获取教材集合
	 * 
	 * @param cmsTextbook
	 * @return
	 */
	List<CmsTextbook> listPage(CmsTextbook cmsTextbook);

	/**
	 * 添加教材
	 * 
	 * @param cmsTextbook
	 * @return
	 */
	Integer addTextbook(CmsTextbook cmsTextbook);

	/**
	 * 删除教材
	 */
	Integer deleteTextbook(Integer id);

	/**
	 * 停启用教材
	 * 
	 */
	Integer confine(Integer id, Integer status);

	/**
	 * 修改教材
	 * 
	 * @param cmsTextbook
	 * @return
	 */
	Integer update(CmsTextbook cmsTextbook);

	/**
	 * 根据textbookId获取该教材的所有节
	 * @param textbookId
	 * @return
	 */
	List<Map<String, Object>> getCatalogTree(int textbookId);
	
	/**
	 * 根据catalogId获取catalog以及视频相关信息
	 * @param list
	 * @return
	 */
	List<CmsCatalogMessage> selectCatalogById(String [] list);
	
	
	/**
	 * 推荐或取消推荐
	 */
	
	int  recommendById(int id);
	
	/**
	 * 根据选课标题 获取选课的数量
	 * @param textbookTitle
	 * @return
	 */
	int getTextbookCountByTextbookTitle(String textbookTitle);
	
	
	
}
