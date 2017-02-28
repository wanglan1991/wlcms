package com.ekt.cms.news.service;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ekt.cms.news.entity.CmsNews;
import com.ekt.cms.news.dao.CmsNewsMapper;

@Service("cmsNewService")
public class CmsNewsService implements ICmsNewsService{
	
	
	@Resource
	private CmsNewsMapper cmsNewsMapper;

	@Override
	public ArrayList<CmsNews> listPage(CmsNews news) {
		return cmsNewsMapper.listPage(news);
	}

	@Override
	public int confineById(int newsId) {
		return cmsNewsMapper.confineById(newsId);
	}

	@Override
	public int delById(int newsId) {
		return cmsNewsMapper.delById(newsId);
	}

	@Override
	public int addNews(CmsNews news) {
		return cmsNewsMapper.addNews(news);
	}

	@Override
	public int editNews(CmsNews news) {
		return cmsNewsMapper.editNews(news);
	}

}
