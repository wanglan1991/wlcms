package com.ekt.cms.common.dao;

import java.util.List;

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
}
