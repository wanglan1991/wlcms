package com.ekt.cms.video.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ekt.cms.common.BaseController;
import com.ekt.cms.common.entity.Result;
import com.ekt.cms.textbook.entity.CmsCatalog;
import com.ekt.cms.textbook.entity.CmsTextbook;
import com.ekt.cms.textbook.service.ICmsCatalogService;
import com.ekt.cms.textbook.service.ICmsTextbookService;
import com.ekt.cms.utils.pageHelper.PageBean;
import com.ekt.cms.utils.pageHelper.PageContext;
import com.ekt.cms.video.entity.CmsVideo;
import com.ekt.cms.video.entity.ExerciseDetail;
import com.ekt.cms.video.entity.Testpaper;
import com.ekt.cms.video.service.ICmsTestpaperService;
import com.ekt.cms.video.service.ICmsVideoService;
/**
 * 2016-05-02
 * 
 * @author zhuyanqiong 视频控制器
 */
@Controller
@RequestMapping("/video")
public class CmsVideoController extends BaseController{
	@Resource
	private ICmsVideoService cmsVideoService;
	
	@Resource
	private ICmsTextbookService  cmsTextbookService;
	
	@Resource
	private ICmsCatalogService cmsCatalogService;
	
	@Resource 
	private ICmsTestpaperService  testpaperService;

	@RequestMapping("/toVideo")
	public String toVideoPage() {
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
			int videoId =Integer.parseInt(id);
			total = total + cmsVideoService.delete(videoId);
			cmsVideoService.removeVideoExerciseByVideoId(videoId);
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
		cmsVideo.setAuthorId(getCurrentAccount().getId());
		cmsVideo.setIsFree(cmsVideo.getPrice()>0?0:1);
		result.setResult(cmsVideoService.update(cmsVideo));
		return result;
	}
	
	//新增
	@RequestMapping("/addVideo")
	@ResponseBody
	public Result insert(CmsVideo cmsVideo){
		Result result =  Result.getResults();
		cmsVideo.setAuthorId(getCurrentAccount().getId());
		cmsVideo.setIsFree(cmsVideo.getPrice()>0?0:1);
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
			result.setResult(excutResult);
		return result;
	}
	
	/**
	 * 生成选课
	 * @param id
	 * @return
	 */
	@RequestMapping("/createTextbook")
	@ResponseBody
	public  Result  createTextbook(@RequestParam("id")int id){
		int result = 0;
		int result2 =0;
		int result3 =0;
		CmsVideo video = cmsVideoService.getVideoById(id);
		CmsTextbook book =video.getTextbook();
		book.setInputAccountId(getCurrentAccount().getId());
		result = cmsTextbookService.addTextbook(book);
		if(result>0){
			CmsCatalog catalog = new CmsCatalog(book.getTitle(),book.getId(),0,0,51,null,null);
			result2 = cmsCatalogService.add(catalog);
			if(result2>0){
				result3 = cmsCatalogService.add(new CmsCatalog(book.getTitle(),book.getId(),0,catalog.getId(),52,book.getDigest(),video.getFileName()));
			}
		}
		return Result.getResults(result3);
	}
	
	
	/**
	 * 生成题库组卷
	 * @param id
	 * @return
	 */
	@RequestMapping("/createTestpaper")
	@ResponseBody
	public Result createTestpaper(@RequestParam("id")int id){
		CmsVideo video = cmsVideoService.getVideoById(id);
		int result =0;
		if(video.getHasTestpaper()>0){
			Result.getResults(result+1);
		}
		List<Integer> ves =testpaperService.getVideoExercises(id);
		Testpaper  tp =new Testpaper();
		tp.setUserId(getCurrentAccount().getEktapiUserId());
		tp.setAuthor("系统题库");
		tp.setTestpaperName(video.getVideoName()+"（课件）");
		tp.setDigest("知识点 ："+video.getKnowledge()+ves.size()+"道基础练习题");
		tp.setSubjectNo(video.getSubjectNo());
		tp.setPhaseNo(tp.getPhaseNo());
		tp.setKnowledgePointArr(video.getKnowledgeId());
		tp.setKnowledgePointArrVal(video.getKnowledge());
		tp.setDifficultyNo(45);
		tp.setCategoryNo(39);
		tp.setVideoId(id);
		result+=testpaperService.insertTestpaper(tp);
		for(int i=0;i<ves.size();i++){
			testpaperService.insertExerciseDetail(new ExerciseDetail(tp.getId(), ves.get(i), getCurrentAccount().getEktapiUserId()));
		}
		return Result.getResults(result);
	}
	
	
	
	

}
