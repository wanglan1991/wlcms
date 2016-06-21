package com.ekt.cms.common.service;

import java.util.List;
import java.util.Map;

import com.ekt.cms.common.entity.CmsDict;
import com.ekt.cms.utils.pageHelper.PageBean;
/**
 * 2016-05-02
 * 
 * @author zhuyanqiong 
 */
public interface ICmsDictService {
	
	public List<CmsDict> queryDictByCondition(CmsDict dict);
	
	public PageBean<CmsDict> listDictPage(CmsDict dict );
	
	public int deleteByPrimaryKey(int id);
	
	public int confine(CmsDict dict);
	
	public CmsDict queryByDictName(String value);
	
	public int insert(CmsDict dict);
	
	public int update(CmsDict dict);
	
	public List<CmsDict> queryTypeName();
	
	public List<Map<String,Object>> famousTeacher();
}
