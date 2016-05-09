package com.ekt.cms.common.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ekt.cms.common.entity.CmsSchool;
import com.ekt.cms.common.entity.Result;
import com.ekt.cms.common.service.CmsSchoolService;
import com.ekt.cms.utils.pageHelper.PageBean;
import com.ekt.cms.utils.pageHelper.PageContext;
/**
 * 2016-05-07
 * 
 * @author zhuyanqiong
 * 地域控制器
 */
@Controller
@RequestMapping("/school")
public class CmsSchoolController {
	@Resource
	private CmsSchoolService cmsSchoolService;
	
	@RequestMapping("/toSchool")
	public String toSchool(){
		return "dict/school";
	}
	//分页查询
	@RequestMapping("/listPage")
	@ResponseBody
	public PageBean<CmsSchool> listPage(PageContext page,CmsSchool cmsSchool){
		page.paging();
		return cmsSchoolService.listPage(cmsSchool);
	}
	//停启用
	@RequestMapping("/confine")
	@ResponseBody
	public Result confine(CmsSchool cmsSchool){
		Result result=Result.getResults();
				result.setResult(cmsSchoolService.Confine(cmsSchool));
		return result;
	}
}
