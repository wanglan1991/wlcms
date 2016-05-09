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

}
