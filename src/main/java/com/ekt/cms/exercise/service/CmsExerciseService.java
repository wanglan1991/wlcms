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

	@Override
	public int exerciseConfine(int id, int status) {
		return cmsExerciseMapper.exerciseConfine(id, status);
	}

	@Override
	public int answerConfine(int id, int status) {
		return cmsExerciseMapper.answerConfine(id, status);
	}

	@Override
	public int deleteExercise(int id) {
		return cmsExerciseMapper.deleteExercise(id);
	}

	@Override
	public int deleteAnswer(int id) {
		return cmsExerciseMapper.deleteAnswer(id);
	}

}
