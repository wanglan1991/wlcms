package com.ekt.cms.common.service;

import java.util.List;

import com.ekt.cms.common.entity.CmsDict;
import com.ekt.cms.utils.pageHelper.PageBean;

public interface ICmsDictService {
	
	public List<CmsDict> queryDictByCondition(CmsDict dict);
	
	public PageBean<CmsDict> listDictPage(CmsDict dict );
	
	public int deleteByPrimaryKey(int id);
	
	public int confine(CmsDict dict);
	
	public CmsDict queryByDictName(String value);
	
	public int insert(CmsDict dict);
	
	public int update(CmsDict dict);
}
