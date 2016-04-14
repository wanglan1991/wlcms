package com.ekt.cms.common.service;

import java.util.List;

import com.ekt.cms.common.entity.Dict;
import com.ekt.cms.utils.pageHelper.PageBean;
import com.ekt.cms.utils.pageHelper.PageContext;

public interface IDictService {
	
	public List<Dict> queryDictByCondition(Dict dict);
	
	public PageBean<Dict> listDictPage(Dict dict ,PageContext pageContext);
	
}
