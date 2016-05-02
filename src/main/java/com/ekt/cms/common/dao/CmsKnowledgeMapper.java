package com.ekt.cms.common.dao;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ekt.cms.common.entity.CmsKnowledge;

public interface CmsKnowledgeMapper {
	/**
	 * 分页查询	 
	 * @param CmsKnowledge
	 * @return
	 */
	List<CmsKnowledge> listPage(@Param("CmsKnowledge")CmsKnowledge cmsKnowledge);
	/**
	 * 更新
	 * @param CmsKnowledge
	 * @return
	 */
	int  updateByPrimaryKey(@Param("CmsKnowledge") CmsKnowledge cmsKnowledge);
	/**
	 * 新增
	 * @param CmsKnowledge
	 * @return
	 */
	int  insert(@Param("CmsKnowledge") CmsKnowledge cmsKnowledge);
	/**
	 * 根据知识点查询
	 * @param CmsKnowledge
	 * @return
	 */
	List<CmsKnowledge>  queryByTitle(@Param("CmsKnowledge") CmsKnowledge cmsKnowledge);
	/**
	 * 启用停用知识点
	 * @param CmsKnowledge
	 * @return
	 */
	int  confine(@Param("CmsKnowledge") CmsKnowledge cmsKnowledge);
	/**
	 * 根据条件查询
	 * @param CmsKnowledge
	 * @return
	 */
	List<CmsKnowledge>  queryByCondition(@Param("CmsKnowledge") CmsKnowledge cmsKnowledge);
	/**
	 * 根据主键删除
	 *  @param id
	 * @return
	 */
	int  delete(Integer id);
	/**
	 * 根据年级Id获得科目List
	 * @param gradeNo
	 * @return
	 */
	List<CmsKnowledge> getSubjectListByGrade(@Param("gradeNo")Integer gradeNo);
	
	
	
	}
