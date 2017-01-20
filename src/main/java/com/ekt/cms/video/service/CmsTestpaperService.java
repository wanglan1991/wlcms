package com.ekt.cms.video.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ekt.cms.video.dao.CmsTestpaperMapper;
import com.ekt.cms.video.entity.ExerciseDetail;
import com.ekt.cms.video.entity.Testpaper;

@Service("testpaperService")
public class CmsTestpaperService implements ICmsTestpaperService {

	@Resource CmsTestpaperMapper  cmsTestpaperMapper;
	@Override
	public int insertTestpaper(Testpaper testpaper) {
		return cmsTestpaperMapper.insertTestpaper(testpaper);
	}
	@Override
	public List<Integer> getVideoExercises(int videoId) {
		return cmsTestpaperMapper.getVideoExercises(videoId);
	}
	@Override
	public int insertExerciseDetail(ExerciseDetail exerciseDetail) {
		return cmsTestpaperMapper.insertExerciseDetail(exerciseDetail);
	}
	@Override
	public int removeTestpaperById(int id) {
		int result = 0;
		result +=cmsTestpaperMapper.removeTestpaperById(id);
		result +=cmsTestpaperMapper.removeTestpaperExerciseByTestpaperId(id);
		if(result ==2){
			return 1;
		}
			return result;
		
	}
	

}
