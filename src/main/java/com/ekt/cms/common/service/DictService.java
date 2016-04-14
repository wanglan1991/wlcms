package com.ekt.cms.common.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ekt.cms.common.dao.DictMapper;
import com.ekt.cms.common.entity.Dict;
import com.ekt.cms.utils.pageHelper.PageBean;
import com.ekt.cms.utils.pageHelper.PageContext;
import com.github.pagehelper.PageHelper;

@Service("dictService")
public class DictService implements IDictService {

	// inject dao
	@Resource
	private DictMapper dictMapper;

	public List<Dict> queryDictByCondition(Dict dict) {
		return dictMapper.queryByCondition(dict);
	}

	@Override
	//分页查询
	public PageBean<Dict> listDictPage(Dict dict ,PageContext pageContext) {
		PageHelper.startPage(PageContext.getPageNum(), PageContext.getPageSize());
		List<Dict> list = this.dictMapper.listDictPage(dict);
		return new PageBean<Dict>(list);
	}
	

}
