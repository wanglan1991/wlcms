package com.ekt.cms.common.service;

import com.ekt.cms.common.entity.CmsSchool;
import com.ekt.cms.utils.pageHelper.PageBean;

public interface ICmsSchoolService {
	//分页查询
	public PageBean<CmsSchool> listPage(CmsSchool CmsSchool);
	//停启用
	public int Confine(CmsSchool CmsSchool);
	//添加学校
	public int addSchool(int cityCode,String shcoolName);
	
	public int findSchoolByName(String schoolName,int cityCode);
	
	public int delSchoolById(int id);
	
	
}
