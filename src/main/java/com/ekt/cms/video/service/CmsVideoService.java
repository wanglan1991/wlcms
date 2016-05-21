package com.ekt.cms.video.service;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.ekt.cms.common.dao.CmsKnowledgeMapper;
import com.ekt.cms.utils.pageHelper.PageBean;
import com.ekt.cms.video.dao.CmsVideoMapper;
import com.ekt.cms.video.entity.CmsVideo;


/**
 * 2016-05-02
 * 
 * @author zhuyanqiong 
 */

@Service("cmsVideoService")
public class CmsVideoService implements ICmsVideoService {
	@Resource
	private CmsVideoMapper cmsVideoMapper;
	@Resource
	private CmsKnowledgeMapper cmsKnowledgeMapper;
	
	@Override
	public PageBean<CmsVideo> listPage(CmsVideo cmsVideo) {
		// TODO Auto-generated method stub
//		List<CmsVideo> CmsVideos=cmsVideoMapper.listPage(cmsVideo);
//		
//		//knowledgeID是一个数组  通过ID获取知识点名字 科目 年级
//		for(CmsVideo Video:CmsVideos ){
//		String knowledgeId=Video.getKnowledgeId();
//		String[] ids=knowledgeId.split(",");
//		List<String> knowledgeList =new  ArrayList<String>();
//		CmsKnowledge cmsKnowledge=new CmsKnowledge();
//		int gradeNo=0;
//		int subjectNo=0;
//		String grade="";
//		String subject="";
//		for(String id:ids){
//			cmsKnowledge.setId(Integer.parseInt(id));
//			List<CmsKnowledge> Knowledges=cmsKnowledgeMapper.listPage(cmsKnowledge);
//			CmsKnowledge knowledge=Knowledges.get(0);
//			String  kString=knowledge.getTitle();
//			 gradeNo=knowledge.getGradeNo();
//			 subjectNo=knowledge.getSubjectNo();
//			 grade=knowledge.getGrade();
//			 subject =knowledge.getSubject();
//			knowledgeList.add(kString);
//			
//		}
//		Video.setKnowledge(knowledgeList);
//		Video.setGrade(grade);
//		Video.setGradeNo(gradeNo);
//		Video.setSubject(subject);
//		Video.setSubjectNo(subjectNo);}
//		return new PageBean<CmsVideo>(CmsVideos);	
 		return new PageBean<CmsVideo>(cmsVideoMapper.listPage(cmsVideo));
	}
	
	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return cmsVideoMapper.delete(id);
	}
	@Override
	public int confine(CmsVideo cmsVideo) {
		// TODO Auto-generated method stub
		return cmsVideoMapper.confine(cmsVideo);
	}
	@Override
	public int update(CmsVideo cmsVideo) {
		// TODO Auto-generated method stub
		return cmsVideoMapper.update(cmsVideo);
	}
	@Override
	public int insert(CmsVideo cmsVideo) {
		// TODO Auto-generated method stub
		return cmsVideoMapper.insert(cmsVideo);
	}

}
