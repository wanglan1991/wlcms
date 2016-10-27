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

/** 
* @ClassName: QuintessenceController 
* @Description: TODO(每日精选控制器) 
* @author wanglan
* @date 2016年8月25日 上午11:20:25 
*  
*/

@Controller
@RequestMapping(value ="/quintessence")
public class CmsQuintessenceController extends BaseController{
	
	@Resource
	private ICmsQuintessenceService quintessenceService;
	
	
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
	 * @return 精选列表
	 */
	@RequestMapping(value = "/listPage")
	@ResponseBody
	public PageBean<CmsQuintessence> listPage(PageContext page, CmsQuintessence quintessence) {
		page.paging();
		return new PageBean<CmsQuintessence>(quintessenceService.listPage(quintessence));
	}
	
	
	/**
	 * 删除 可批量删除
	 * @param ids
	 * @return 操作是否成功
	 */
	@RequestMapping(value = "/delete")
	@ResponseBody
	public  Result deleteById(@RequestParam("ids")String ids){
		Result result = Result.getResults();
		String[]arr =ids.split(",");
		int total=0;
		if(arr.length>0){
			for(int i=0;i<arr.length;i++){
				total+=quintessenceService.delQuintessenceById(Integer.parseInt(arr[i]));
			}
		}
			result.setResult(total);		
		return result;
		
	}
	/**
	 * 获取每日精选
	 * 1:组卷
	 * 2:课程
	 * 3:文选
	 * @param type
	 * @return
	 */
	@RequestMapping(value = "/contentList")
	@ResponseBody
	public Result getQuintessenceContentList(@RequestParam("contentType")int type){
		Result result = Result.getResults();
		result.setValue(type==1?quintessenceService.getQuintessenceTestpaper():
						type==2?quintessenceService.getQuintessenceTextbook():null);		
		return result;
	}
	
	/**
	 * 添加精选
	 * @return
	 */
	@RequestMapping(value = "/add")
	@ResponseBody
	public Result addQuintessence(CmsQuintessence cmsQuintessence){
		Result result = Result.getResults();
		if(quintessenceService.getQuintessence(cmsQuintessence).size()<1){
			cmsQuintessence.setInputAccountId(getCurrentAccount().getId());
		result.setResult(quintessenceService.addQuintessence(cmsQuintessence));
		}else{
		result.setResult(-1);
		result.setMsg("无法添加，该记录已经存在！");
		}
		return result;
	}
	
	
	

}
