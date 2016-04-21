package com.ekt.cms.common.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ekt.cms.common.dao.CmsKnowledgeMapper;
import com.ekt.cms.common.entity.CmsKnowledge;
import com.ekt.cms.utils.pageHelper.PageBean;

@Service("cmsKnowledgeService")
public class CmsKnowledgeService implements ICmsKnowledgeService {
	@Resource
	private CmsKnowledgeMapper CmsKnowledgeMapper;

	@Override
	public PageBean<CmsKnowledge> listPage(CmsKnowledge cmsKnowledge) {
		// TODO Auto-generated method stub
		return new PageBean<CmsKnowledge>(CmsKnowledgeMapper.listPage(cmsKnowledge));
	}

}
