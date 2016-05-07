package com.ekt.cms.common.service;

import com.ekt.cms.common.entity.CmsRegion;
import com.ekt.cms.utils.pageHelper.PageBean;

public interface ICmsRegionService {
	//分页查询
	public PageBean<CmsRegion> listPage(CmsRegion cmsRegion);

}
