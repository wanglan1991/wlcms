package com.ekt.cms.common.service;

import java.util.List;

import com.ekt.cms.common.entity.Dict;

public interface IDictService {
	
	public List<Dict> queryDictByCondition(Dict dict);
	
}
