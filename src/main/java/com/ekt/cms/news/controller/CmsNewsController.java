package com.ekt.cms.news.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.ekt.cms.common.BaseController;
import com.ekt.cms.common.entity.Result;
import com.ekt.cms.news.entity.CmsNews;
import com.ekt.cms.news.service.ICmsNewsService;
import com.ekt.cms.utils.pageHelper.PageBean;
import com.ekt.cms.utils.pageHelper.PageContext;



@Controller
@RequestMapping("/news")
public class CmsNewsController extends BaseController{
	
	@Resource
	private ICmsNewsService cmsNewService;
	
	/**
	 * 动态资讯管理页面
	 * @return
	 */
	@RequestMapping("/manager")
	@ResponseBody
	public ModelAndView newsManage(){
		return new ModelAndView("/main/news/newsManage");
	}
	
	
	/**
	 * 动态资讯列表 带分页
	 * @param page
	 * @param news
	 * @return
	 */
	@RequestMapping("/listPage")
	@ResponseBody
	public PageBean<CmsNews> listPage(PageContext page,CmsNews news){
		page.paging();
		return new PageBean<CmsNews>(cmsNewService.listPage(news));
	}
	
	/**
	 * 添加动态
	 * @param news
	 * @return
	 */
	@RequestMapping("/addNews")
	@ResponseBody
	public Result addNews(CmsNews news){
		return Result.getResults(cmsNewService.addNews(news));
	}
	/**
	 * 编辑动态
	 * @param news
	 * @return
	 */
	
	@RequestMapping("/editNews")
	@ResponseBody
	public Result editNews(CmsNews news){
		return Result.getResults(cmsNewService.editNews(news));
	}
	/**
	 * 停用或启用动态
	 * @param news
	 * @return
	 */
	@RequestMapping("/confine")
	@ResponseBody
	public Result confine(CmsNews news){
		return Result.getResults(cmsNewService.confineById(news.getId()));
	}
	

	/**
	 * 删除动态
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/del")
	@ResponseBody
	public Result del(String ids){
	String [] idsArr =ids.split(",");
	int result = 0;
	if(idsArr.length>0){
		for(int i=0;i<idsArr.length;i++){
			result += cmsNewService.delById(Integer.parseInt(idsArr[i]));
		}
	}
		return Result.getResults(result);
	}
	

}
