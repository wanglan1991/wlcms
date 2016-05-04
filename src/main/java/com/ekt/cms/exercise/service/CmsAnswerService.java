package com.ekt.cms.exercise.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ekt.cms.exercise.dao.CmsAnswerMapper;
import com.ekt.cms.exercise.entity.CmsAnswer;
/**
 * 
 * @author wanglan
 * 2016-05-01 
 * 习题答案service实现类
 *
 */
@Service("cmsAnswerService")
public class CmsAnswerService implements ICmsAnswerService {

	@Resource
	private CmsAnswerMapper cmsAnswerMapper;

	public List<CmsAnswer> getAnswerList(Integer exerciseId) {
		return cmsAnswerMapper.getAnswerList(exerciseId);
	}

}
