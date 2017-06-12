package com.ekt.cms.agency.service;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ekt.cms.agency.dao.CmsAgencyMapper;
import com.ekt.cms.agency.entity.CmsAgency;

@Service("agencyService")
public class CmsAgencyService implements CmsIAgencyService {

	@Resource
	private CmsAgencyMapper agencyMapper;

	@Override
	public ArrayList<CmsAgency> getAgencyList(CmsAgency agency) {
		return agencyMapper.getAgencyList(agency);
	}
}