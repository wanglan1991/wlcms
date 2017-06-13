package com.ekt.cms.common.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ekt.cms.common.dao.CmsTimingTaskMapper;

@Service("timingTaskService")
public class CmsTimingTaskService implements ICmsTimingTaskService {

	
	@Resource
	private CmsTimingTaskMapper  cmsTimingTaskMapper;
	@Override
	public int insertTodayActiveReportData() {
		return cmsTimingTaskMapper.insertTodayActiveReportData();
	}
	@Override
	public int returnCoupons() {
		// TODO Auto-generated method stub
		return cmsTimingTaskMapper.updateCouponsStatus();
	}

}
