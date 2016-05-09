package com.ekt.cms.common.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ekt.cms.common.dao.CmsRegionMapper;
import com.ekt.cms.common.entity.CmsRegion;
import com.ekt.cms.utils.pageHelper.PageBean;
/**
 * 2016-05-07
 * 
 * @author zhuyanqiong
 */
@Service("cmsRegionService")
public class CmsRegionService implements ICmsRegionService {
	@Resource
	private CmsRegionMapper CmsRegionMapper;
	@Override
	public PageBean<CmsRegion> listPage( CmsRegion cmsRegion) {
		// TODO Auto-generated method stub
		
		return new PageBean<CmsRegion>(CmsRegionMapper.listPage(cmsRegion));
	}
	@Override
	public int Confine(CmsRegion cmsRegion) {
		// TODO Auto-generated method stub
		return CmsRegionMapper.confine(cmsRegion);
	}

}
