package com.ekt.cms.video.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ekt.cms.video.entity.CmsVideo;

public interface CmsVideoMapper {
	/**
	 * 分页查询	 
	 * @param cmsVideo
	 * @return
	 */
	List<CmsVideo> listPage(@Param("CmsVideo")CmsVideo cmsVideo);
	/**
	 * 按主键删除	 
	 * @param id
	 * @return
	 */
	int delete(int id);
	/**
	 * 更新停启用状态	 
	 * @param CmsVideo
	 * @return
	 */
	int confine(@Param("CmsVideo")CmsVideo cmsVideo);
	/**
	 * 更新	 
	 * @param CmsVideo
	 * @return
	 */
	int update(@Param("CmsVideo")CmsVideo cmsVideo);
}
