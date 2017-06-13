package com.ekt.cms.video.service;

import java.util.List;
import com.ekt.cms.video.entity.ExerciseDetail;
import com.ekt.cms.video.entity.Testpaper;

public interface ICmsTestpaperService {

	/**
	 * 插入题库组卷
	 * @param testpaper
	 * @return
	 */
	int insertTestpaper(Testpaper testpaper);
	
	
	/**
	 * 获取视频课件习题
	 * @param videoId
	 * @return
	 */
	List<Integer> getVideoExercises(int videoId);
	
	/**
	 * 插入组卷详情
	 * @param exerciseDetail
	 * @return
	 */
	int insertExerciseDetail(ExerciseDetail exerciseDetail);
	
	/**
	 *  删除组卷
	 * @param id
	 * @return
	 */
	int removeTestpaperById(int id);
	
	/**
	 * 修改组卷
	 * @param quintessence
	 * @return
	 */
	int updateTestpaper(Testpaper testpaper);
	
	
	
}
