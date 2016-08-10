package com.ekt.cms.teacher.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSONObject;
import com.ekt.cms.common.entity.Result;
import com.ekt.cms.teacher.entity.CmsTeacher;
import com.ekt.cms.teacher.service.ICmsTeacherService;
import com.ekt.cms.utils.pageHelper.PageBean;
import com.ekt.cms.utils.pageHelper.PageContext;
import java.util.List;
import java.util.Map;



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
	
	/**
	 * 根据id删除名师
	 * @param ids
	 * @return
	 */
	@RequestMapping(value="/delete")
	@ResponseBody
	public Result delete(@RequestParam("ids")String ids){
		Result result = Result.getResults();
		String[] arr = ids.split(",");
		int total = 0;
		for (String id : arr) {
			total += cmsTeacherService.deleteTeacher(Integer.parseInt(id));
		}
		result.setResult(total);
		return result;
	}
	
	/**
	 * 添加荣誉
	 * @param teacherId
	 * @param arr
	 * @return
	 */
	@Transactional
	@RequestMapping(value="/addHonours")
	@ResponseBody
	public Result addHonours(@RequestParam("teacherId")int teacherId,@RequestParam("arr")String arr){
		Result result =Result.getResults();
		List<Object> list=JSONObject.parseArray(arr);
		cmsTeacherService.removeHonoursByTeacherId(teacherId);
		int count = 0;
		for(Object obj:list){
			Map<String,Object> map=(Map<String, Object>) obj;			
			map.put("teacherId", teacherId);
			count+=cmsTeacherService.addHonours(map);
		}
		result.setResult(count);			
		return result;
	}
	
	/**
	 * 获取荣誉集合
	 * @param teacherId
	 * @return
	 */
	@RequestMapping(value="/getHonours")
	@ResponseBody
	public Result getHonours(@RequestParam("teacherId")int teacherId){
		Result result =Result.getResults();
		result.setValue(cmsTeacherService.getHonours(teacherId));		
		return result;		
	}
	
	
	

}
