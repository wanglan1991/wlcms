package com.ekt.cms.textbook.service;

import java.util.List;

import com.ekt.cms.textbook.entity.CmsTextbook;

/**
 * 
 * @author wanglan
 *	2016-05-02
 */
public interface ICmsTextbookService {
	/**
	 * 获取教材集合
	 * @param cmsTextbook
	 * @return
	 */
	List<CmsTextbook> listPage(CmsTextbook cmsTextbook);

}
