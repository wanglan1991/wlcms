package com.ekt.cms.common.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ekt.cms.common.entity.CmsRegion;

/**
 * 2016-05-07
 * 
 * @author zhuyanqiong
 */
public interface CmsRegionMapper {
	/**
	 * 分页查询	 
	 * @param CmsRegion
	 * @return
	 */
	List<CmsRegion> listPage(@Param("CmsRegion")CmsRegion CmsRegion);
	/**
	 * 停启用	 
	 * @param CmsRegion
	 * @return
	 */
	int  confine(@Param("CmsRegion")CmsRegion CmsRegion);
}
