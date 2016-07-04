package com.ekt.cms.teacher.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ekt.cms.common.entity.Result;
import com.ekt.cms.teacher.entity.CmsTeacher;
import com.ekt.cms.teacher.service.ICmsTeacherService;
import com.ekt.cms.utils.pageHelper.PageBean;
import com.ekt.cms.utils.pageHelper.PageContext;

/**
 * @author wanglan
 * @email wanglan-TD@foxmail.com
 * @version 创建时间：2016年6月20日 上午10:49:08 程序的简单说明
 * 
 *          教师控制器
 */

@Controller
@RequestMapping(value = "/teacher")
public class CmsTeacherController {

	@Resource
	private ICmsTeacherService cmsTeacherService;

	/**
	 * 教师管理页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/manager")
	public String teacherManager() {
		return "main/teacher/teacherManage";
	};

	/**
	 * 获取名师列表 可带参查询
	 * 
	 * @param page
	 * @param cmsTeacher
	 * @return
	 */
	@RequestMapping(value = "/listPage")
	@ResponseBody
	public PageBean<CmsTeacher> listPage(PageContext page, CmsTeacher cmsTeacher) {
		page.paging();
		return new PageBean<CmsTeacher>(cmsTeacherService.listPage(cmsTeacher));
	}

	/**
	 * 添加教师
	 * 
	 * @param cmsTeacher
	 * @return
	 */
	@RequestMapping(value = "/add")
	@ResponseBody
	public Result addTeacher(CmsTeacher cmsTeacher) {
		Result result = Result.getResults();
		if (cmsTeacher.getUserId() > 0) {
			CmsTeacher teacher = cmsTeacherService.getCmsTeacherByUserIdAndTeacherName(cmsTeacher.getUserId(),
					cmsTeacher.getName());
			if(teacher!=null){
				result.setResult(-1);
				result.setMsg("该教师已经存在！");
				return result;
			}
		}
		cmsTeacher.setHeadPicture("</headPicture/" + cmsTeacher.getHeadPicture() + "/>");
		result.setResult(cmsTeacherService.insertTeacher(cmsTeacher));
		return result;
	}

}
