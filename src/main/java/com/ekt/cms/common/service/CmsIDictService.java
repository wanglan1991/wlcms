package com.ekt.cms.common.service;

import java.util.List;

import com.ekt.cms.common.entity.CmsDict;
import com.ekt.cms.utils.pageHelper.PageBean;
import com.ekt.cms.utils.pageHelper.PageContext;

public interface CmsIDictService {
	
	public List<CmsDict> queryDictByCondition(CmsDict dict);
	
	public PageBean<CmsDict> listDictPage(CmsDict dict );
	
}
