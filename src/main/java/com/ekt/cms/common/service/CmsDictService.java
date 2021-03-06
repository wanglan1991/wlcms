package com.ekt.cms.common.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ekt.cms.common.dao.CmsDictMapper;
import com.ekt.cms.common.entity.CmsDict;
import com.ekt.cms.utils.pageHelper.PageBean;

/**
 * 2016-05-02
 * 
 * @author zhuyanqiong 
 */

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

	@Override
	public List<CmsDict> queryTypeName() {
		// TODO Auto-generated method
		return dictMapper.queryTypeName();
	}

	@Override
	public List<Map<String, Object>> famousTeacher() {
		// TODO Auto-generated method stub
		return dictMapper.famousTeacher();
	}

	@Override
	public CmsDict exerciseQueryByDictNameAndEncoding(String value, String encoding) {
		return dictMapper.exerciseQueryByDictNameAndEncoding(value, encoding);
	}

}
