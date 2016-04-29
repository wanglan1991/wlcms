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
	Integer exerciseConfine(int id,int status);
	/**
	 * 根据id停启用答案
	 * @param id
	 * @param status
	 * @return
	 */
	Integer answerConfine(int id,int status);
	/**
	 * 根据习题id删除习题
	 * @param id
	 * @return
	 */
	Integer deleteExercise(int id);
	
	/**
	 * 根据习题Id删除答案
	 * @param id
	 * @return
	 */
	Integer deleteAnswer(int id);
	
	
	/**
	 * 插入习题返回插入的记录自增的ID
	 * @param exercise
	 * @return
	 */
	Integer insertExercise(CmsExercise exercise);
	
	/**
	 * 根据习题id插入答案
	 * @param exerciseId
	 * @param option
	 * @param content
	 * @param isTrue
	 * @return
	 */
	Integer insertAnswer(Integer exerciseId,String option,String content,int isTrue);
	

}
