package com.ekt.cms.common.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ekt.cms.common.dao.CmsDictMapper;
import com.ekt.cms.common.entity.CmsDict;
import com.ekt.cms.utils.pageHelper.PageBean;

@Service("dictService")
public class CmsDictService implements ICmsDictService {

	// inject dao
	@Resource
	private CmsDictMapper dictMapper;

	public List<CmsDict> queryDictByCondition(CmsDict dict) {
		return dictMapper.queryByCondition(dict);
	}

	@Override
	//分页查询
	public PageBean<CmsDict> listDictPage(CmsDict dict ) {
		List<CmsDict> list =this.dictMapper.listDictPage(dict);
		return new PageBean<CmsDict>(list);
	}

	@Override
	public int deleteByPrimaryKey(int id) {
		// TODO Auto-generated method stub
		return dictMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int confine(CmsDict dict) {
		// TODO Auto-generated method stub
		return dictMapper.confine(dict);
	}

	@Override
	public CmsDict queryByDictName(String value) {
		// TODO Auto-generated method stub
		return dictMapper.queryByDictName(value);
	}

	@Override
	public int insert(CmsDict dict) {
		// TODO Auto-generated method stub
		return dictMapper.insert(dict);
	}

	@Override
	public int update(CmsDict dict) {
		// TODO Auto-generated method stub
		return dictMapper.updateByPrimaryKey( dict);
	}

}
