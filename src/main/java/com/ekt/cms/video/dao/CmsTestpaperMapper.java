package com.ekt.cms.video.dao;

import java.util.List;

import com.ekt.cms.video.entity.ExerciseDetail;
import com.ekt.cms.video.entity.Testpaper;

public interface CmsTestpaperMapper {

	
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
	 * 根据组卷id删除组卷习题
	 * @param id
	 * @return
	 */
	int removeTestpaperExerciseByTestpaperId(int id);
	
	
}
