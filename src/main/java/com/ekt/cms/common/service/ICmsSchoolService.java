package com.ekt.cms.common.service;

import com.ekt.cms.common.entity.CmsSchool;
import com.ekt.cms.utils.pageHelper.PageBean;

public interface ICmsSchoolService {
	//分页查询
	public PageBean<CmsSchool> listPage(CmsSchool CmsSchool);
	//停启用
	public int Confine(CmsSchool CmsSchool);
}
