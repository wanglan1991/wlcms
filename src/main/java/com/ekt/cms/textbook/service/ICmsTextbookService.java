package com.ekt.cms.textbook.service;

import java.util.List;
import java.util.Map;

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
	 * @param cmsTextbook
	 * @return
	 */
	Integer update(CmsTextbook cmsTextbook);

}
