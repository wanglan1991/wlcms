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
	List<CmsExercise> pageList(CmsExercise exercise);
	/**
	 * 停启用习题
	 * @param id
	 * @param status
	 * @return
	 */
	int exerciseConfine(int id,int status);
	/**
	 * 根据id停启用答案
	 * @param id
	 * @param status
	 * @return
	 */
	int answerConfine(int id,int status);
	/**
	 * 根据习题id删除习题
	 * @param id
	 * @return
	 */
	int deleteExercise(int id);
	
	/**
	 * 根据习题Id删除答案
	 * @param id
	 * @return
	 */
	int deleteAnswer(int id);
	

}
