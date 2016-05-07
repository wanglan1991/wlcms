package com.ekt.cms.common.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ekt.cms.common.entity.CmsRegion;
import com.ekt.cms.common.service.CmsRegionService;
import com.ekt.cms.utils.pageHelper.PageBean;
import com.ekt.cms.utils.pageHelper.PageContext;
/**
 * 2016-05-07
 * 
 * @author zhuyanqiong
 * 地域控制器
 */
@Controller
@RequestMapping("/region")
public class CmsRegionController {
	@Resource
	private CmsRegionService cmsRegionService;
	
	@RequestMapping("/toRegion")
	public String toRegion(){
		return "dict/region";
	}
	
	@RequestMapping("/listPage")
	@ResponseBody
	public PageBean<CmsRegion> listPage(PageContext page,CmsRegion cmsRegion){
		page.paging();
		return cmsRegionService.listPage(cmsRegion);
	}
}
