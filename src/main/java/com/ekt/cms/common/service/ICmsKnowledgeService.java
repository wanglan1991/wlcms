package com.ekt.cms.common.service;

import com.ekt.cms.common.entity.CmsKnowledge;
import com.ekt.cms.utils.pageHelper.PageBean;

public interface ICmsKnowledgeService {
	// 分页查询
	PageBean<CmsKnowledge> listPage(CmsKnowledge cmsKnowledge);
}
