package com.ekt.cms.account.service;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ekt.cms.account.dao.CmsLoginRecordMapper;
import com.ekt.cms.account.entity.CmsLoginRecord;
@Service("loginRecordService")
public class CmsLoginRecordService implements ICmsLoginRecordService{
	
	@Resource
	private CmsLoginRecordMapper cmsloginRecordMapper;

	@Override
	public ArrayList<CmsLoginRecord> getCmsLoginRecordList(CmsLoginRecord cr) {
		return cmsloginRecordMapper.getCmsLoginRecordList(cr);
	}

	@Override
	public int insertLoginRecord(CmsLoginRecord cr) {
		return cmsloginRecordMapper.insertLoginRecord(cr);
	}

}
