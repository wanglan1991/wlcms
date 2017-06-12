package com.ekt.cms.report.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ekt.cms.report.dao.CmsUserActiveReportMapper;
import com.ekt.cms.report.entity.UserActiveReport;
import com.ekt.cms.report.entity.UserActiveReportValidBean;

/**
 * 
* @Title: cmsUserActiveReportService.java 
* @Package com.ekt.cms.report.service 
* @Description: TODO(ICmsUserActiveReportService 实现类) 
* @author wanglan
* @date 2017年3月30日 下午4:12:08 
* @version V1.0
 */
@Service("userActiveReportService")
public class cmsUserActiveReportService implements ICmsUserActiveReportService {
	
	@Resource
	private CmsUserActiveReportMapper cmsUserActiveReportMapper;

	@Override
	public List<UserActiveReport> getList(UserActiveReportValidBean uarvb) {
		return cmsUserActiveReportMapper.getList(uarvb);
	}

}
