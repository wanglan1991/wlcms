package com.ekt.cms.common.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ekt.cms.common.dao.CmsSchoolMapper;
import com.ekt.cms.common.entity.CmsSchool;
import com.ekt.cms.utils.pageHelper.PageBean;
/**
 * 2016-05-07
 * 
 * @author zhuyanqiong
 */
@Service("cmsSchoolService")
public class CmsSchoolService implements ICmsSchoolService {
	@Resource
	private CmsSchoolMapper CmsSchoolMapper;
	@Override
	public PageBean<CmsSchool> listPage( CmsSchool cmsSchool) {
		// TODO Auto-generated method stub
		
		return new PageBean<CmsSchool>(CmsSchoolMapper.listPage(cmsSchool));
	}
	@Override
	public int Confine(CmsSchool cmsSchool) {
		// TODO Auto-generated method stub
		return CmsSchoolMapper.confine(cmsSchool);
	}
	@Override
	public int addSchool(int cityCode, String schoolName) {
		return CmsSchoolMapper.addSchool(cityCode,schoolName);
	}
	@Override
	public int findSchoolByName(String schoolName,int cityCode) {
		return CmsSchoolMapper.findSchoolByName(schoolName,cityCode);
	}
	@Override
	public int delSchoolById(int id) {
		return CmsSchoolMapper.delSchoolById(id);
	}
	
	

}
