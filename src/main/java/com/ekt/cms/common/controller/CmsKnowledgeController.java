package com.ekt.cms.common.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ekt.cms.account.entity.CmsAccount;
import com.ekt.cms.common.entity.CmsKnowledge;
import com.ekt.cms.common.service.CmsKnowledgeService;
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
		public PageBean<CmsKnowledge> accountList(PageContext page ,CmsKnowledge cmsKnowledge){
			page.paging();
			return cmsKnowledgeService.listPage(cmsKnowledge);
		}
}
