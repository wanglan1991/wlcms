package com.ekt.cms.exercise.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ekt.cms.exercise.dao.CmsExerciseMapper;
import com.ekt.cms.exercise.entity.CmsExercise;
/**
 * 
 * @author wanglan 2016-04-25 
 *
 */
@Service("cmsExerciseService")
public class CmsExerciseService implements ICmsExerciseService {
	@Resource
	private CmsExerciseMapper cmsExerciseMapper;

	@Override
	public List<CmsExercise> pageList(CmsExercise exercise) {
		return cmsExerciseMapper.pageList(exercise);
	}

}
