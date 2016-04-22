package com.ekt.cms.common.service;

import java.util.List;

import com.ekt.cms.common.entity.CmsKnowledge;
import com.ekt.cms.utils.pageHelper.PageBean;

public interface ICmsKnowledgeService {
	// 分页查询
	PageBean<CmsKnowledge> listPage(CmsKnowledge cmsKnowledge);
	//根据主键更新
	int updateByPrimaryKey(CmsKnowledge cmsKnowledge);
	//新增
	int insert(CmsKnowledge cmsKnowledge);
	//根据知识点查询
	List<CmsKnowledge>  queryByTitle(CmsKnowledge cmsKnowledge);
	//启用 停用知识点
	int confine(CmsKnowledge cmsKnowledge);
	// 按条件查询
	PageBean<CmsKnowledge> queryByCondition(CmsKnowledge cmsKnowledge);
	//按主键删除
	int delete(int id);
}
