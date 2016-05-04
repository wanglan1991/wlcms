package com.ekt.cms.common.service;

import java.util.List;
import java.util.Map;

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

	@Override
	public int updateByPrimaryKey(CmsKnowledge cmsKnowledge) {
		// TODO Auto-generated method stub
		return CmsKnowledgeMapper.updateByPrimaryKey(cmsKnowledge);
	}

	@Override
	public int insert(CmsKnowledge cmsKnowledge) {
		// TODO Auto-generated method stub
		return CmsKnowledgeMapper.insert(cmsKnowledge);
	}

	@Override
	public List<CmsKnowledge> queryByTitle(CmsKnowledge cmsKnowledge) {
		// TODO Auto-generated method stub
		return CmsKnowledgeMapper.queryByTitle(cmsKnowledge);
	}

	@Override
	public int confine(CmsKnowledge cmsKnowledge) {
		// TODO Auto-generated method stub
		return CmsKnowledgeMapper.confine(cmsKnowledge);
	}

	@Override
	public PageBean<CmsKnowledge> queryByCondition(CmsKnowledge cmsKnowledge) {
		// TODO Auto-generated method stub
		return new PageBean<CmsKnowledge>(CmsKnowledgeMapper.queryByCondition(cmsKnowledge));
	}
	@Override
	public  int delete(int id){
		return CmsKnowledgeMapper.delete(id);
	}

	@Override
	public List<CmsKnowledge> query(CmsKnowledge cmsKnowledge) {
		// TODO Auto-generated method stub
		return CmsKnowledgeMapper.queryByCondition(cmsKnowledge);
	}

	public List<CmsKnowledge> getSubjectListByGrade(Integer grade) {
		// TODO Auto-generated method stub
		return CmsKnowledgeMapper.getSubjectListByGrade(grade);
	}

	@Override
	public List<CmsKnowledge> knowledgelist(CmsKnowledge cmsKnowledge) {
		// TODO Auto-generated method stub
		return CmsKnowledgeMapper.knowledgelist(cmsKnowledge);
	}

	@Override
	public List<Map<String, Object>> knowledgeTree(int gradeNo, int subjectNo) {
		// TODO Auto-generated method stub
		return CmsKnowledgeMapper.knowledgeTree(gradeNo,subjectNo);
	}
}
