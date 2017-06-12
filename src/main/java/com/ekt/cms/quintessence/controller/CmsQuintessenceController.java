/**   
* @Title: QuintessenceController.java 
* @Package com.ekt.cms.quintessence.controller 
* @Description: TODO(用一句话描述该文件做什么) 
* @author wanglan
* @date 2016年8月25日 上午11:20:25 
* @version V1.0   
*/
package com.ekt.cms.quintessence.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ekt.cms.common.BaseController;
import com.ekt.cms.common.entity.Result;
import com.ekt.cms.quintessence.entity.CmsQuintessence;
import com.ekt.cms.quintessence.service.ICmsQuintessenceService;
import com.ekt.cms.utils.pageHelper.PageBean;
import com.ekt.cms.utils.pageHelper.PageContext;
import com.ekt.cms.video.entity.Testpaper;
import com.ekt.cms.video.service.ICmsTestpaperService;

/** 
* @ClassName: QuintessenceController 
* @Description: TODO(题库选控制器) 
* @author wanglan
* @date 2016年8月25日 上午11:20:25 
*  
*/

@Controller
@RequestMapping(value ="/quintessence")
public class CmsQuintessenceController extends BaseController{
	
	@Resource
	private ICmsQuintessenceService quintessenceService;
	
	
	@Resource 
	private ICmsTestpaperService testpaperService;
	
	
	/**
	 * 每日精选管理页面
	 * @return
	 */
	@RequestMapping(value="/manager")
	public String manager(){		
		return "main/quintessence/quintessenceManage";	
	}
	
	/**
	 * 加载列表 带分页
	 * @param page
	 * @param quintessence
	 * @return 题库列表
	 */
	@RequestMapping(value = "/listPage")
	@ResponseBody
	public PageBean<CmsQuintessence> listPage(PageContext page, CmsQuintessence quintessence) {
		page.paging();
		return new PageBean<CmsQuintessence>(quintessenceService.listPage(quintessence));
	}
	/**
	 * 获取teacher列表
	 * @return
	 */
	@RequestMapping(value = "/teacherList")
	@ResponseBody
	public Result getTeacherList(){
		return Result.getResults(quintessenceService.getTeacherList());
	}
	
	
	/**
	 * app每日精选习题、题库组卷习题。隐藏或显示
	 * @return
	 */
	@RequestMapping(value = "/confine")
	@ResponseBody
	public Result confine(String ids){
		int result = 0;
		String [] idsArr =ids.split(",");
		if(idsArr.length!=0){
			for(int i=0;i<idsArr.length;i++){
				result += quintessenceService.confine(Integer.parseInt(idsArr[i]));
			}
		}
		return Result.getResults(result);
		
	}
	
	/**
	 * 删除题库组卷
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
			result += testpaperService.removeTestpaperById(Integer.parseInt(idsArr[i]));
		}
	}
		return Result.getResults(result);
	}
	
	
	/**
	 * 编辑精选组卷
	 * @param quintessence
	 * @return
	 */
	@RequestMapping(value = "/edit")
	@ResponseBody
	public Result edit(Testpaper testpaper){
		return Result.getResults(testpaperService.updateTestpaper(testpaper));
	}
	
	

}
