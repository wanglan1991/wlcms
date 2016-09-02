package com.ekt.cms.video.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ekt.cms.common.entity.Result;
import com.ekt.cms.utils.pageHelper.PageBean;
import com.ekt.cms.utils.pageHelper.PageContext;
import com.ekt.cms.video.entity.CmsVideo;
import com.ekt.cms.video.service.ICmsVideoService;
/**
 * 2016-05-02
 * 
 * @author zhuyanqiong 视频控制器
 */
@Controller
@RequestMapping("/video")
public class CmsVideoController {
	@Resource
	private ICmsVideoService cmsVideoService;

	@RequestMapping("/toVideo")
	public String toVideoPage() {
//		return "main/video/vidoe";
		return "main/video/videoManage";
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
	
	//新增
	@RequestMapping("/addVideo")
	@ResponseBody
	public Result insert(CmsVideo cmsVideo){
		Result result =  Result.getResults();
		result.setResult(cmsVideoService.insert(cmsVideo));
		return result;
		}
	
	
	/**
	 * 根据 gradeNo、subjectNo、videoId获取视频习题tree
	 * @param cmsVideo
	 * @return
	 */
	@RequestMapping("/exerciseTree")
	@ResponseBody
	public Result getVideoExerciseTree(CmsVideo cmsVideo){
		Result result =Result.getResults();
		result.setValue(cmsVideoService.getVideoExerciseTree(cmsVideo));
		return result;
	} 
	
	/**
	 * 跟新视频配套习题
	 * @param arr
	 * @return
	 */
	@RequestMapping("/updateVideoExercise")
	@ResponseBody
	public Result updateVideoExerciseTree(@RequestParam("arr")String arr,
			@RequestParam("videoId")int videoId){
		Result result =Result.getResults();
		int excutResult =cmsVideoService.removeVideoExerciseByVideoId(videoId);
			String[] ids =arr.split(",");
			if(arr!=""){
				for(int i=0;i<ids.length;i++){
					cmsVideoService.addVideoExerciseTree(Integer.parseInt(ids[i]), videoId, i);
				}	
			}
			result.setResult(1);
		return result;
	}
	

}
