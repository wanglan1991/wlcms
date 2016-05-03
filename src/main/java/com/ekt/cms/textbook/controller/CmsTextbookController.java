package com.ekt.cms.textbook.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ekt.cms.textbook.entity.CmsTextbook;
import com.ekt.cms.textbook.service.ICmsTextbookService;
import com.ekt.cms.utils.pageHelper.PageBean;
import com.ekt.cms.utils.pageHelper.PageContext;

/**
 * 2016-05-02
 * 
 * @author wanglan 教材控制器
 */
@Controller
@RequestMapping("/textbook")
public class CmsTextbookController {

	/**
	 * 返回教材页面
	 * 
	 * @return
	 */
	@Resource
	private ICmsTextbookService cmsTextbookService;
	
	@RequestMapping("/manage")
	public String textbookManage() {
		return "/main/textbook/manage";
	}

	/**
	 * 教材集合
	 * 
	 * @param pageContext
	 * @param cmsTextbook
	 * @return
	 */
	@RequestMapping("/pageList")
	@ResponseBody
	public PageBean<CmsTextbook> textbookPageList(PageContext pageContext, CmsTextbook cmsTextbook) {
		pageContext.paging();
		return new PageBean<CmsTextbook>(cmsTextbookService.listPage(cmsTextbook));
	}

}
