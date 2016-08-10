package com.ekt.cms.common.service;

import java.util.List;
import java.util.Map;

import com.ekt.cms.common.entity.CmsRegion;
import com.ekt.cms.utils.pageHelper.PageBean;

public interface ICmsRegionService {
	//分页查询
	public PageBean<CmsRegion> listPage(CmsRegion cmsRegion);
	//停启用
	public int Confine(CmsRegion cmsRegion);
	//根据父级id获取CityList
	List<Map<String,Object>> getRegionList(int parentCode);
}
