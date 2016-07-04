package com.ekt.cms.video.service;

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
}
