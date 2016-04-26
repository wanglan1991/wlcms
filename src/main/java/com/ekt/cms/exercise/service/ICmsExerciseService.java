package com.ekt.cms.exercise.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ekt.cms.exercise.entity.CmsExercise;

/**
 * 
 * @author wanglan 2016-04-25 15:57 习题接口
 */
public interface ICmsExerciseService {
	/**
	 * 加载习题列表
	 * @param exercise
	 * @return
	 */
	List<CmsExercise> pageList(@Param("exercise") CmsExercise exercise);

}
