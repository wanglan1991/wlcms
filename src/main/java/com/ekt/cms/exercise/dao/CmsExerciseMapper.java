package com.ekt.cms.exercise.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ekt.cms.exercise.entity.CmsExercise;

/**
 * 
 * @author wanglan  2016-04-25  习题控制器
 *
 */
public interface CmsExerciseMapper {
	//获取习题列表
	List<CmsExercise> pageList(@Param("exercise")CmsExercise exercise);

}
