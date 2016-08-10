package com.ekt.cms.common.service;

import java.util.List;
import java.util.Map;

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
	private CmsRegionMapper cmsRegionMapper;
	@Override
	public PageBean<CmsRegion> listPage( CmsRegion cmsRegion) {
		// TODO Auto-generated method stub
		
		return new PageBean<CmsRegion>(cmsRegionMapper.listPage(cmsRegion));
	}
	@Override
	public int Confine(CmsRegion cmsRegion) {
		// TODO Auto-generated method stub
		return cmsRegionMapper.confine(cmsRegion);
	}
	@Override
	public List<Map<String, Object>> getRegionList(int parentCode) {
		// TODO Auto-generated method stub
		return cmsRegionMapper.getRegionList(parentCode);
	}

}
