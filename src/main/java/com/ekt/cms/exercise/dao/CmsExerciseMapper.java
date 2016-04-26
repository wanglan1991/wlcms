package com.ekt.cms.exercise.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ekt.cms.exercise.entity.CmsExercise;

/**
 * 
 * @author wanglan 2016-04-25 习题控制器
 *
 */
public interface CmsExerciseMapper {
	// 获取习题列表
	List<CmsExercise> pageList(@Param("exercise") CmsExercise exercise);

	// 停用习题
	int exerciseConfine(@Param("id") int id, @Param("status") int status);

	// 停用答案
	int answerConfine(@Param("id") int id, @Param("status") int status);

	// 根据习题id删除习题
	int deleteExercise(@Param("id")int id);

	// 根据习题id删除习题答案
	int deleteAnswer(@Param("id")int id);

}
