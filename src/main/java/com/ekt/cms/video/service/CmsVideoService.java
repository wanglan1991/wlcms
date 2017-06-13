package com.ekt.cms.video.service;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.ekt.cms.common.dao.CmsKnowledgeMapper;
import com.ekt.cms.common.entity.TreeBean;
import com.ekt.cms.utils.pageHelper.PageBean;
import com.ekt.cms.video.dao.CmsVideoMapper;
import com.ekt.cms.video.entity.CmsVideo;


/**
 * 2016-05-02
 * 
 * @author zhuyanqiong 
 */

@Service("cmsVideoService")
public class CmsVideoService implements ICmsVideoService {
	@Resource
	private CmsVideoMapper cmsVideoMapper;
	@Resource
	private CmsKnowledgeMapper cmsKnowledgeMapper;
	
	@Override
	public PageBean<CmsVideo> listPage(CmsVideo cmsVideo) {
 		return new PageBean<CmsVideo>(cmsVideoMapper.listPage(cmsVideo));
	}
	
	@Override
	public int delete(int id) {
		return cmsVideoMapper.delete(id);
	}
	@Override
	public int confine(CmsVideo cmsVideo) {
		return cmsVideoMapper.confine(cmsVideo);
	}
	@Override
	public int update(CmsVideo cmsVideo) {
		return cmsVideoMapper.update(cmsVideo);
	}
	@Override
	public int insert(CmsVideo cmsVideo) {
		return cmsVideoMapper.insert(cmsVideo);
	}

	@Override
	public int updateByVideoKey(CmsVideo cmsVideo) {
		return cmsVideoMapper.updateByVideoKey(cmsVideo);
	}

	@Override
	public List<TreeBean> getVideoExerciseTree(CmsVideo cmsVideo) {
		return cmsVideoMapper.getVideoExerciseTree(cmsVideo);
	}

	

	@Override
	public int removeVideoExerciseByVideoId(int videoId) {
		return cmsVideoMapper.removeVideoExerciseByVideoId(videoId);
	}

	@Override
	public int addVideoExerciseTree(int exerciseId, int videoId, int orderNo) {
		return cmsVideoMapper.addVideoExerciseTree(exerciseId, videoId, orderNo);
	}

	@Override
	public int updateVideoTransStatusByFileId(String videoKey,int status) {
		return cmsVideoMapper.updateVideoTransStatusByFileId(videoKey,status);
	}

	

	@Override
	public int updateVideoBySubKey(CmsVideo cmsVideo) {
		return cmsVideoMapper.updateVideoBySubKey(cmsVideo);
	}

	@Override
	public CmsVideo getVideoById(int id) {
		return cmsVideoMapper.getVideoById(id);
	}

	@Override
	public int updateVideoSubKeyByVideoId(int videoId, String subVideoKey) {
		return cmsVideoMapper.updateVideoSubKeyByVideoId(videoId, subVideoKey);
	}

	

	

	
}
