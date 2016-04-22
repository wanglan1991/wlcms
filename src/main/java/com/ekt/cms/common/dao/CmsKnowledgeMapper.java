package com.ekt.cms.common.dao;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ekt.cms.common.entity.CmsKnowledge;

public interface CmsKnowledgeMapper {
	/**
	 * 分页查询	 
	 * @param cmsDict
	 * @return
	 */
	List<CmsKnowledge> listPage(@Param("CmsKnowledge")CmsKnowledge cmsKnowledge);
	/**
	 * 更新
	 * @param cmsDict
	 * @return
	 */
	int  updateByPrimaryKey(@Param("CmsKnowledge") CmsKnowledge cmsKnowledge);
	/**
	 * 新增
	 * @param cmsDict
	 * @return
	 */
	int  insert(@Param("CmsKnowledge") CmsKnowledge cmsKnowledge);
	/**
	 * 新增
	 * @param cmsDict
	 * @return
	 */
	List<CmsKnowledge>  queryByCondition(@Param("CmsKnowledge") CmsKnowledge cmsKnowledge);
	}
