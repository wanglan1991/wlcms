package com.ekt.cms.teacher.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ekt.cms.teacher.entity.CmsTeacher;

/** 
* @author wanglan 
* @email wanglan-TD@foxmail.com
* @version 创建时间：2016年6月20日 上午11:47:08 
* 程序的简单说明 
*/
public interface CmsTeacherMapper {
	
	/**
	 *  可带参 获取教师List
	 * @param cmsTeacher
	 * @return  List<CmsTeacher>
	 */
	public List<CmsTeacher> listPage(@Param("cmsTeacher")CmsTeacher cmsTeacher);
	
	/**
	 * 新增插入教师
	 * @param cmsTeacher
	 * @return
	 */
	public int insertTeacher(@Param("cmsTeacher")CmsTeacher cmsTeacher);
	
	/**
	 * 根据教师名以及用户id获取教师对象
	 * @param userId
	 * @param teacherName
	 * @return
	 */
	
	public CmsTeacher getCmsTeacherByUserIdAndTeacherName(@Param("userId")int userId,@Param("teacherName")String teacherName);

}
