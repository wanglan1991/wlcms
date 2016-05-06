package com.ekt.cms.video.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ekt.cms.common.entity.Result;
import com.ekt.cms.utils.pageHelper.PageBean;
import com.ekt.cms.utils.pageHelper.PageContext;
import com.ekt.cms.video.entity.CmsVideo;
import com.ekt.cms.video.service.ICmsVideoService;

import freemarker.core.ReturnInstruction.Return;

@Controller
@RequestMapping("/video")
public class CmsVideoController {
	@Resource
	private ICmsVideoService cmsVideoService;

	@RequestMapping("/toVideo")
	public String toVideoPage() {
		return "main/video/manage";
	}

	// 分页查询
	@RequestMapping("/listPage")
	@ResponseBody
	public PageBean<CmsVideo> videoList(PageContext page, CmsVideo cmsVideo) {
		page.paging();
		return cmsVideoService.listPage(cmsVideo);
	}

	// 按主键删除
	@RequestMapping("/delete")
	@ResponseBody
	public Result delete(String ids) {
		Result result = Result.getResults();
		String[] arr = ids.split(",");
		int total = 0;
		for (String id : arr) {
			total = total + cmsVideoService.delete(Integer.parseInt(id));
		}
		result.setResult(total);
		return result;
	}
	
	//停启用
	@RequestMapping("/confine")
	@ResponseBody
	public Result confine(CmsVideo cmsVideo){
		Result result = Result.getResults();
		result.setResult(cmsVideoService.confine(cmsVideo));
		return result;
	}
	
	//更新
	@RequestMapping("/editVideo")
	@ResponseBody
	public Result update(CmsVideo cmsVideo){
		Result result =  Result.getResults();
		result.setResult(cmsVideoService.update(cmsVideo));
		return result;
	}

}
