package com.ekt.cms.exercise.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ekt.cms.exercise.entity.CmsAnswer;
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
	Integer exerciseConfine(@Param("id") int id, @Param("status") int status);

	// 停用答案
	Integer answerConfine(@Param("id") int id, @Param("status") int status);

	// 根据习题id删除习题
	Integer deleteExercise(@Param("id") int id);

	// 根据习题id删除习题答案
	Integer deleteAnswer(@Param("id") int id);

	// 插入习题并返回插入的插入记录的自增id
	Integer insertExercise(CmsExercise exercise);
	//插入习题答案
	Integer insertAnswer(@Param("exerciseId") Integer exerciseId, @Param("option") String option,
			@Param("content") String content, @Param("isTrue") int isTrue);
	//修改习题
	Integer updateExercise(CmsExercise exercise);
	//根据习题id获取习题以及答案list
	List<Map<String,Object>> getExerciseById(@Param("exerciseId")Integer exerciseId);
	
}
