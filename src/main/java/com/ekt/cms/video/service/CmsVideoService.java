package com.ekt.cms.video.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ekt.cms.utils.pageHelper.PageBean;
import com.ekt.cms.video.dao.CmsVideoMapper;
import com.ekt.cms.video.entity.CmsVideo;
@Service("cmsVideoService")
public class CmsVideoService implements ICmsVideoService {
	@Resource
	private CmsVideoMapper cmsVideoMapper;
	@Override
	public PageBean<CmsVideo> listPage(CmsVideo cmsVideo) {
		// TODO Auto-generated method stub
		
		return new PageBean<CmsVideo>(cmsVideoMapper.listPage(cmsVideo));
	}
	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return cmsVideoMapper.delete(id);
	}
	@Override
	public int confine(CmsVideo cmsVideo) {
		// TODO Auto-generated method stub
		return cmsVideoMapper.confine(cmsVideo);
	}
	@Override
	public int update(CmsVideo cmsVideo) {
		// TODO Auto-generated method stub
		return cmsVideoMapper.update(cmsVideo);
	}
	@Override
	public int insert(CmsVideo cmsVideo) {
		// TODO Auto-generated method stub
		return cmsVideoMapper.insert(cmsVideo);
	}

}
