package com.ekt.cms.exercise.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ekt.cms.exercise.entity.CmsAnswer;
/**
 * 
 * @author wanglan
 * 2016-05-01 习题DAO
 *
 */
public interface CmsAnswerMapper {
	//根据习题id查询答案list
		List<CmsAnswer> getAnswerList(@Param("exerciseId")int exerciseId);
}
