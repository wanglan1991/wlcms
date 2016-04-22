package com.ekt.cms.common.controller;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ekt.cms.common.entity.CmsKnowledge;
import com.ekt.cms.common.entity.Result;
import com.ekt.cms.common.service.ICmsKnowledgeService;
import com.ekt.cms.utils.pageHelper.PageBean;
import com.ekt.cms.utils.pageHelper.PageContext;

@Controller
@RequestMapping(value="/knowledge")  //知识点controller
public class CmsKnowledgeController {
	
	@Resource
	private ICmsKnowledgeService cmsKnowledgeService;
	 @RequestMapping("/toKnowledge")
	    public String toDictPage() {
	    	return "dict/knowledge";
	    }
	 
		@RequestMapping("/listPage")
		@ResponseBody
		public PageBean<CmsKnowledge> knowledgeList(PageContext page ,CmsKnowledge cmsKnowledge){
			page.paging();
			return cmsKnowledgeService.listPage(cmsKnowledge);
		}
		
		@RequestMapping("/editKnowledge")
		@ResponseBody	
		public Result update(CmsKnowledge cmsKnowledge){
			Result result=new Result();
			result.setResult(cmsKnowledgeService.updateByPrimaryKey(cmsKnowledge));
			return result;
		}
		
		@RequestMapping("/addKnowledge")
		@ResponseBody
		public Result insert(CmsKnowledge cmsKnowledge){
			Result result=new Result();
			List<CmsKnowledge> list=cmsKnowledgeService.queryByCondition(cmsKnowledge);
			if( list.size()>0){
				result.setMsg("该知识点已存在");
			}else {
				result.setResult(cmsKnowledgeService.insert(cmsKnowledge));
			}
			return result;
		}

}
