package com.ekt.cms.common.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ekt.cms.common.entity.CmsSchool;

/**
 * 2016-05-07
 * 
 * @author zhuyanqiong
 */
public interface CmsSchoolMapper {
	/**
	 * 分页查询	 
	 * @param CmsSchool
	 * @return
	 */
	List<CmsSchool> listPage(@Param("CmsSchool")CmsSchool CmsSchool);
	/**
	 * 停启用	 
	 * @param CmsRegion
	 * @return
	 */
	int  confine(@Param("CmsSchool")CmsSchool CmsSchool);
	
	/**
	 * 添加学校
	 * @param cityCode
	 * @param schoolName
	 * @return
	 */
	int addSchool(@Param("cityCode")int cityCode,@Param("schoolName")String schoolName);
	 /**
	  * 根据学校名称获取学校的count
	  * @param schoolName
	  * @param cityCode
	  * @return
	  */
	int findSchoolByName(@Param("schoolName")String schoolName,@Param("cityCode")int cityCode);
	/**
	 * 根据学校id删除该学校
	 * @param id
	 * @return
	 */
    int delSchoolById(int id); 
    
    /**
     * 根据cityCode获取该cityCode中的所有学校
     * @param cityCode
     * @return
     */
    public List<Map<String, Object>> schoolListByCityCode(@Param("cityCode")String cityCode);
	
}
