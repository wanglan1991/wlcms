package com.ekt.cms.exercise.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ekt.cms.exercise.dao.CmsExerciseMapper;
import com.ekt.cms.exercise.entity.CmsAnswer;
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
	public Integer exerciseConfine(int id, int status) {
		return cmsExerciseMapper.exerciseConfine(id, status);
	}

	@Override
	public Integer answerConfine(int id, int status) {
		return cmsExerciseMapper.answerConfine(id, status);
	}

	@Override
	public Integer deleteExercise(int id) {
		return cmsExerciseMapper.deleteExercise(id);
	}

	@Override
	public Integer deleteAnswer(int id) {
		return cmsExerciseMapper.deleteAnswer(id);
	}

	@Override
	public Integer insertExercise(CmsExercise exercise) {
		return cmsExerciseMapper.insertExercise(exercise);
	}

	@Override
	public Integer insertAnswer(Integer exerciseId, String option, String content, int isTrue) {
		return cmsExerciseMapper.insertAnswer(exerciseId,option,content,isTrue);
	}

	public Integer updateExercise(CmsExercise exercise) {
		return cmsExerciseMapper.updateExercise(exercise) ;
	}

	

}
