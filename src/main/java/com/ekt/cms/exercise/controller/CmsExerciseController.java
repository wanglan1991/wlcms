package com.ekt.cms.exercise.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ekt.cms.common.BaseController;
import com.ekt.cms.exercise.entity.CmsExercise;
import com.ekt.cms.exercise.service.ICmsExerciseService;
import com.ekt.cms.utils.pageHelper.PageBean;
import com.ekt.cms.utils.pageHelper.PageContext;

/**
 * 习题控制器
 * 2016-04-25
 * @author wanglan
 *
 */
@Controller
@RequestMapping(value="/exercise")
public class CmsExerciseController extends BaseController{
	
	@Resource
	private ICmsExerciseService cmsExerciseService;
	
	/**
	 * 返回习题列表页面
	 * @return
	 */
	@RequestMapping("/manage")
	public String manage(){
		return "main/exercise/manage";
	}
	/**
	 * 加载习题列表
	 * @param page
	 * @param exercise
	 * @return
	 */
	@RequestMapping("/pageList")
	@ResponseBody
	public PageBean<CmsExercise>pageList(PageContext page ,CmsExercise exercise){
		page.paging();
		return new PageBean<CmsExercise>(cmsExerciseService.pageList(exercise));
	}

}
