package com.ekt.cms.common.service;

import com.ekt.cms.common.entity.CmsSchool;
import com.ekt.cms.utils.pageHelper.PageBean;
import java.util.List;
import java.util.Map;

public interface ICmsSchoolService {
	//分页查询
	public PageBean<CmsSchool> listPage(CmsSchool CmsSchool);
	//停启用
	public int Confine(CmsSchool CmsSchool);
	//添加学校
	public int addSchool(int cityCode,String shcoolName);
	//根据学校名称以及City获取学校
	public int findSchoolByName(String schoolName,int cityCode);
	//根据学校id删除
	public int delSchoolById(int id);
	//根据citycode获取该cityCode中的所有学校
	public List<Map<String,Object>> schoolListByCityCode(String cityCode);
	
	
}
