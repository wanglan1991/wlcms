package com.ekt.cms.agency.service;

import java.util.ArrayList;

import com.ekt.cms.agency.entity.CmsAgency;

public interface CmsIAgencyService {
	
	/**
	 * 获取代理商列表
	 * @param agency
	 * @return
	 */
	 ArrayList<CmsAgency> getAgencyList(CmsAgency agency);

}
