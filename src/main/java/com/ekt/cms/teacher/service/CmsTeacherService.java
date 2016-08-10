package com.ekt.cms.teacher.service;

import java.util.List;
import java.util.Map;

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

	@Override
	public int insertTeacher(CmsTeacher cmsTeacher) {
		return cmsTeacherMapper.insertTeacher(cmsTeacher);
	}

	@Override
	public CmsTeacher getCmsTeacherByUserIdAndTeacherName(int userId, String teacherName) {
		return cmsTeacherMapper.getCmsTeacherByUserIdAndTeacherName(userId, teacherName);
	}

	@Override
	public int deleteTeacher(int id) {
		return cmsTeacherMapper.deleteTeacher(id);
	}

	@Override
	public int addHonours(Map<String, Object> map) {
		return cmsTeacherMapper.addHonours(map);
	}

	@Override
	public List<Map<String, Object>> getHonours(int teacherId) {
		return cmsTeacherMapper.getHonours(teacherId);
	}

	@Override
	public int removeHonoursByTeacherId(int teacherId) {
		return cmsTeacherMapper.removeHonoursByTeacherId(teacherId);
	}

}