package com.ekt.cms.account.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ekt.cms.account.entity.CmsAccount;
import com.ekt.cms.account.entity.CmsLoginRecord;
import com.ekt.cms.account.service.ICmsLoginRecordService;
import com.ekt.cms.utils.pageHelper.PageBean;
import com.ekt.cms.utils.pageHelper.PageContext;

@Controller
@RequestMapping("/loginRecord")
public class CmsLoginRecordController {
	
	@Resource
	private ICmsLoginRecordService loginRecordService;
	
	@RequestMapping(value = "/manage")
	public String manage() {
		return "/main/loginRecord/loginRecordManage";
	}
	
	@RequestMapping("/listPage")
	@ResponseBody
	public PageBean<CmsLoginRecord> accountList(PageContext page,CmsLoginRecord loginRecord) {
		page.paging();
		return new PageBean<CmsLoginRecord>(loginRecordService.getCmsLoginRecordList(loginRecord));
		
		
	}
}
