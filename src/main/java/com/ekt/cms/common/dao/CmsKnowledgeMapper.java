package com.ekt.cms.common.dao;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ekt.cms.common.entity.CmsKnowledge;
/**
 * 2016-05-02
 * 
 * @author zhuyanqiong 
 */
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
	
	/**
	 * 
	 * 根据年级 科目查询知识点List
	 * @param cmsKnowledge
	 * @return
	 */
	List<CmsKnowledge> knowledgelist(@Param("CmsKnowledge") CmsKnowledge cmsKnowledge);
	/**
	 * 根据年级 科目获取知识点Tree
	 * @param gradeNo
	 * @param subjectNo
	 * @return
	 */
	List<Map<String, Object>> knowledgeTree(@Param("gradeNo")int gradeNo, @Param("subjectNo")int subjectNo);
	
	/**
	 * 根据知识点名称获取知识点对象
	 * @param name
	 * @return
	 */
	CmsKnowledge getKnowledgeByName(String name);
	
	}
