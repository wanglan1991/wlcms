package com.ekt.cms.common.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ekt.cms.common.dao.DictMapper;
import com.ekt.cms.common.entity.Dict;

@Service("dictService")
public class DictService implements IDictService {

	// inject dao
	@Resource
	private DictMapper dictMapper;

	public List<Dict> queryDictByCondition(Dict dict) {
		return dictMapper.queryByCondition(dict);
	}

}
