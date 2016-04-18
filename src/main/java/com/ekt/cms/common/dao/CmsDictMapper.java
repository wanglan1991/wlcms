package com.ekt.cms.common.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ekt.cms.common.entity.CmsDict;

public interface CmsDictMapper {
	/**
	 * 根据用户id删除
	 * 
	 * @param cmsDict
	 * @return
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * 插入
	 * @param cmsDict
	 * @return
	 */
	int insert(CmsDict dict);

	/**
	 * 根据用户id查询
	 * 
	 * @param cmsDict
	 * @return
	 */
	CmsDict selectByPrimaryKey(Integer id);

	/**
	 * 根据用户id更新
	 * 
	 * @param cmsDict
	 * @return
	 */
	int updateByPrimaryKey(CmsDict dict);

	/**
	 * 按条件查询
	 * 
	 * @param cmsDict
	 * @return
	 */
	public List<CmsDict> queryByCondition(@Param("CmsDict") CmsDict dict);

	/**
	 * 分页查询
	 * 
	 * @param cmsDict
	 * @return
	 */
	public List<CmsDict> listDictPage(@Param("CmsDict") CmsDict dict);

	/**
	 * 停用或启用用户 
	 * @param cmsDict
	 * @return
	 */
	int confine(@Param("CmsDict") CmsDict dict);
	/**
	 * 根据字典值查询
	 * @param value
	 * @return cmsDict
	 */
	CmsDict queryByDictName(String value);

}