package com.ekt.cms.teacher.service;

import java.util.List;

import com.ekt.cms.teacher.entity.CmsTeacher;
/** 
* @author wanglan 
* @email wanglan-TD@foxmail.com
* @version 创建时间：2016年6月20日 上午11:42:43 
* 程序的简单说明 
*/
public interface ICmsTeacherService {
	
	/**
	 * 可带参获取教师List
	 * @param cmsTeacher
	 * @return List<CmsTeacher> 
	 */
	public List<CmsTeacher> listPage(CmsTeacher cmsTeacher);
	
	/**
	 * 新增教师
	 * @param cmsTeacher
	 * @return
	 */
	public int insertTeacher(CmsTeacher cmsTeacher);
	
	/**
	 * 根据教师名称以及userId获取名师
	 * @return
	 */
	public CmsTeacher getCmsTeacherByUserIdAndTeacherName(int userId,String teacherName);

}
