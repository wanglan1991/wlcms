package com.ekt.cms.common.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ekt.cms.common.dao.CmsDictMapper;
import com.ekt.cms.common.entity.CmsDict;
import com.ekt.cms.utils.pageHelper.PageBean;
import com.ekt.cms.utils.pageHelper.PageContext;
import com.github.pagehelper.PageHelper;

@Service("dictService")
public class CmsDictService implements CmsIDictService {

	// inject dao
	@Resource
	private CmsDictMapper dictMapper;

	public List<CmsDict> queryDictByCondition(CmsDict dict) {
		return dictMapper.queryByCondition(dict);
	}

	@Override
	//分页查询
	public PageBean<CmsDict> listDictPage(CmsDict dict ) {
		List<CmsDict> list = this.dictMapper.listDictPage(dict);
		return new PageBean<CmsDict>(list);
	}
	

}
