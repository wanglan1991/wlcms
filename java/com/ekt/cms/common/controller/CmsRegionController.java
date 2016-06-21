package com.ekt.cms.common.controller;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ekt.cms.common.entity.CmsRegion;
import com.ekt.cms.common.entity.Result;
import com.ekt.cms.common.service.ICmsRegionService;
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
	private ICmsRegionService cmsRegionService;
	
	@RequestMapping("/toRegion")
	public String toRegion(){
		return "dict/region";
	}
	//分页查询
	@RequestMapping("/listPage")
	@ResponseBody
	public PageBean<CmsRegion> listPage(PageContext page,CmsRegion cmsRegion){
		page.paging();
		return cmsRegionService.listPage(cmsRegion);
	}
	//停启用
	@RequestMapping("/confine")
	@ResponseBody
	public Result confine(CmsRegion cmsRegion){
		Result result=Result.getResults();
				result.setResult(cmsRegionService.Confine(cmsRegion));
		return result;
	}
	
	
	@RequestMapping("/getRegionList")
	@ResponseBody
	public Result getRegionList(@RequestParam("parentCode")int parentCode){
		Result result=Result.getResults();
		result.setValue(cmsRegionService.getRegionList(parentCode));
		return result;
		
		
	}
	
}
