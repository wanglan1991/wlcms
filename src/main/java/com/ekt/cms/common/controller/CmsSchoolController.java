package com.ekt.cms.common.controller;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	/**
	 * 批量添加学校
	 * @param cityCode
	 * @param schoolNameArr
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Result add(@RequestParam("cityCode")int cityCode,@RequestParam("schoolNameArr")String schoolNameArr){
		Result result = Result.getResults();
		if(cityCode<1||schoolNameArr==null){
			result.setMsg("非法参数！");
		}
		String [] schoolNames =schoolNameArr.split(",");
		int count =0;
		String schools="";
		for(String schoolName:schoolNames){
			if(cmsSchoolService.findSchoolByName(schoolName,cityCode)>0){
				schools+=schoolName+",";
			}else{
			cmsSchoolService.addSchool(cityCode, schoolName);
			count+=1;
			}
		}
		if(schools!=""){
			result.setMsg(schools+"已存在！无法被添加！");
		}
		
		result.setResult(count);
		return result;
		
	}
	
	/**
	 * 根据id删除或批量删除
	 * 
	 * @param cmsCatalog
	 * @return
	 */
	@Transactional
	@RequestMapping("/delete")
	@ResponseBody
	public Result delete(@RequestParam("ids") String ids) {
		Result result = Result.getResults();
		String[] arr = ids.split(",");
		int total = 0;
		for (String id : arr) {
			total += cmsSchoolService.delSchoolById(Integer.parseInt(id));
		}
		result.setResult(total);
		return result;
	}

	/**
	 * 根据cityCode获取学校List
	 * @param cmsSchool
	 * @return
	 */
	@RequestMapping("/schoolList")
	@ResponseBody
	public Result schoolListByCityCode(CmsSchool cmsSchool){
		Result result = Result.getResults();
		result.setValue(cmsSchoolService.schoolListByCityCode(cmsSchool.getCityCode()));
		return result;
	}
	
	
	
	
}
