package com.ekt.cms.teacher.dao;

import java.util.List;
import java.util.Map;

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
	
	/**
	 * 根据id删除教师
	 * @param id
	 * @return
	 */
	public int deleteTeacher(int id);
	
	/**
	 * 添加名师荣誉
	 * @param map
	 * @return
	 */
	public int addHonours(Map<String, Object> map);
	
	/**
	 * 获取荣誉列表
	 * @param teacherId
	 * @return
	 */
	public List<Map<String,Object>> getHonours(int teacherId);
	
	/**
	 * 根据教师id删除其荣誉信息
	 * @param teacherId
	 * @return
	 */
	public int removeHonoursByTeacherId(int teacherId);
	


}
