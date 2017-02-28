package com.ekt.cms.news.service;

import java.util.ArrayList;

import com.ekt.cms.news.entity.CmsNews;

public interface ICmsNewsService {
	
	/**
	 * 获取动态资讯列表
	 * @param news
	 * @return
	 */
	public ArrayList<CmsNews> listPage(CmsNews news);
	

	/**
	 * 根据动态资讯id停启用
	 * @param newsId
	 * @return
	 */
	public int confineById(int newsId);
	
	
	/**
	 * 根据动态id删除动态
	 * @param newsId
	 * @return
	 */
	public int delById(int newsId);
	
	/**
	 * 添加动态
	 * @param news
	 * @return
	 */
	public int addNews(CmsNews news);
	
	/**
	 * 修改动态
	 * @param news
	 * @return
	 */
	public int editNews(CmsNews news);
	

}
