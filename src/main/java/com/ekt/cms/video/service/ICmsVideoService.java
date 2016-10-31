package com.ekt.cms.video.service;

import java.util.List;
import com.ekt.cms.common.entity.TreeBean;
import com.ekt.cms.utils.pageHelper.PageBean;
import com.ekt.cms.video.entity.CmsVideo;
/**
 * 2016-05-02
 * 
 * @author zhuyanqiong 
 */

public interface ICmsVideoService {
	//分页查询
	PageBean<CmsVideo> listPage(CmsVideo cmsVideo);
	//按主键删除
	int delete(int id);
	//更新停启用状态
	int confine(CmsVideo cmsVideo);
	//更新
	int update(CmsVideo cmsVideo);
	//新增
	int insert(CmsVideo cmsVideo);
	//转码更新
	int updateByVideoKey(CmsVideo cmsVideo);
	//根据年级、学科、知识点、视频id获取参照tree
	List<TreeBean> getVideoExerciseTree(CmsVideo cmsVideo);
	//更新视频配套习题
	int addVideoExerciseTree(int exerciseId,int videoId,int orderNo);
	//删除视频配套习题
	int removeVideoExerciseByVideoId(int videoId);
	//更新转码状态
	int updateVideoTransStatusByFileId(String videoKey,int status);
	//用户视频id 获取视频对象
	CmsVideo getVideoById(int id);
	
	
}
