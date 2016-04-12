package com.ekt.cms.menu.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ekt.cms.menu.dao.CmsMenuMapper;
import com.ekt.cms.menu.entity.CmsMenu;
import com.ekt.cms.region.dao.RegionMapper;
import com.ekt.cms.region.entity.Region;
import com.ekt.cms.utils.page.Pagination;

@Service("cmsMenuService")
public class CmsMenuService implements ICmsMenuService {
	
	//inject dao
	@Resource
	private CmsMenuMapper cmsMenuMapper;

	@Override
	public int addCmsMenu(CmsMenu cmsMenu) throws Exception {
		return cmsMenuMapper.insert(cmsMenu);
	}

	@Override
	public CmsMenu queryByKey(int key) throws Exception {
		return cmsMenuMapper.selectByPrimaryKey(key);
	}

	@Override
	public int deleteByKey(int key) throws Exception {
		return cmsMenuMapper.deleteByPrimaryKey(key);
	}

	@Override
	public int updateByKey(CmsMenu cmsMenu) throws Exception {
		return cmsMenuMapper.updateByPrimaryKey(cmsMenu);
	}

	@Override
	public List<CmsMenu> queryByCondition(CmsMenu cmsMenu) {
		return cmsMenuMapper.queryByCondition(cmsMenu);
	}

	@Override
	public List<CmsMenu> listPage(CmsMenu cmsMenu, Pagination pagination) {
		return cmsMenuMapper.listPage(cmsMenu, pagination);
	}



}
