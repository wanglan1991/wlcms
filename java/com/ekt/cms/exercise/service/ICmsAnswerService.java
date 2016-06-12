package com.ekt.cms.exercise.service;

import java.util.List;

import com.ekt.cms.exercise.entity.CmsAnswer;

public interface ICmsAnswerService {
	/**
	 * 根据习题Id查询答案list
	 * @param exerciseId
	 * @return
	 */
	public List<CmsAnswer>getAnswerList(Integer exerciseId);

}
