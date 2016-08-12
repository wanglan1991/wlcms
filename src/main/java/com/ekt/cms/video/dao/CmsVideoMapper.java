package com.ekt.cms.video.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ekt.cms.common.entity.TreeBean;
import com.ekt.cms.video.entity.CmsVideo;
/**
 * 2016-05-02
 * 
 * @author zhuyanqiong 
 */
public interface CmsVideoMapper {
	/**
	 * 分页查询	 
	 * @param cmsVideo
	 * @return
	 */
	List<CmsVideo> listPage(@Param("CmsVideo")CmsVideo cmsVideo);
	/**
	 * 按主键删除	 
	 * @param id
	 * @return
	 */
	int delete(int id);
	/**
	 * 更新停启用状态	 
	 * @param CmsVideo
	 * @return
	 */
	int confine(@Param("CmsVideo")CmsVideo cmsVideo);
	/**
	 * 更新	 
	 * @param CmsVideo
	 * @return
	 */
	int update(@Param("CmsVideo")CmsVideo cmsVideo);
	/**
	 * 新增	 
	 * @param CmsVideo
	 * @return
	 */
	int insert(@Param("CmsVideo")CmsVideo cmsVideo);
	/**
	 * 根据videoKey更新URL 时长 文件名 
	 * @param CmsVideo
	 * @return
	 */
	int updateByVideoKey(@Param("CmsVideo")CmsVideo cmsVideo);
	
	/**
	 * 根据年级、学科、知识点、视频id获取参照tree
	 * @param cmsVideo
	 * @return
	 */
	List<TreeBean> getVideoExerciseTree(@Param("CmsVideo")CmsVideo cmsVideo);
	
	//更新视频配套习题
	int addVideoExerciseTree(@Param("exerciseId")int exerciseId,@Param("videoId")int videoId,@Param("orderNo")int orderNo);
	
	//删除视频配套习题
	int removeVideoExerciseByVideoId(int videoId);
	
}
