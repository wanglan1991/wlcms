package com.ekt.cms.teacher.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ekt.cms.teacher.dao.CmsTeacherMapper;
import com.ekt.cms.teacher.entity.CmsTeacher;

/** 
* @author wanglan 
* @email wanglan-TD@foxmail.com
* @version 创建时间：2016年6月20日 上午11:43:12 
* 程序的简单说明 
*/

@Service("cmsTeacherService")
public class CmsTeacherService implements ICmsTeacherService {
	
	@Resource
	private CmsTeacherMapper cmsTeacherMapper;

	@Override
	public List<CmsTeacher> listPage(CmsTeacher cmsTeacher){
		return cmsTeacherMapper.listPage(cmsTeacher);
	}

}
