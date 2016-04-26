package com.ekt.cms.exercise.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ekt.cms.common.BaseController;
import com.ekt.cms.common.entity.Result;
import com.ekt.cms.exercise.entity.CmsExercise;
import com.ekt.cms.exercise.service.ICmsExerciseService;
import com.ekt.cms.utils.pageHelper.PageBean;
import com.ekt.cms.utils.pageHelper.PageContext;

/**
 * 习题控制器 2016-04-25
 * 
 * @author wanglan
 *
 */
@Controller
@RequestMapping(value = "/exercise")
public class CmsExerciseController extends BaseController {

	@Resource
	private ICmsExerciseService cmsExerciseService;

	/**
	 * 返回习题列表页面
	 * 
	 * @return
	 */
	@RequestMapping("/manage")
	public String manage() {
		return "main/exercise/manage";
	}

	/**
	 * 加载习题列表
	 * 
	 * @param page
	 * @param exercise
	 * @return
	 */
	@RequestMapping("/pageList")
	@ResponseBody
	public PageBean<CmsExercise> pageList(PageContext page, CmsExercise exercise) {
		page.paging();
		return new PageBean<CmsExercise>(cmsExerciseService.pageList(exercise));
	}

	/**
	 * 停用或启用习题
	 * 
	 * @param id
	 * @param status
	 * @return
	 */
	@Transactional
	@RequestMapping("/confine")
	@ResponseBody
	public Result confine(@RequestParam("id") Integer id, @RequestParam("status") Integer status) {
		Result result = Result.getResults();
		// 停用或启用习题以及对应的答案
		if (cmsExerciseService.exerciseConfine(id, status) > 0 || cmsExerciseService.answerConfine(id, status) > 0) {
			result.setResult(1);
		}
		return result;

	}

	/**
	 * 根据Id删除习题
	 * 
	 * @param ids
	 * @return
	 */
	@Transactional
	@RequestMapping("/delete")
	@ResponseBody
	public Result exercise(@RequestParam("ids") String ids) {
		Result result = Result.getResults();
		int total = 0;
		String[] arr = ids.split(",");

		for (int i = 0; i < arr.length; i++) {
			// 删除习题
			total += cmsExerciseService.deleteExercise(Integer.parseInt(arr[i].toString()));
			// 删除答案
			cmsExerciseService.deleteAnswer(Integer.parseInt(arr[i].toString()));
		}
		result.setResult(total);
		return result;

	}

}
