package com.ekt.cms.textbook.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ekt.cms.textbook.dao.CmsCatalogMapper;
import com.ekt.cms.textbook.entity.CmsCatalog;
/**
 * @author wanglan
 * 2016-05-05
 * 教材目录接口实现类 service
 */
@Service("cmsCatalogService")
public class CmsCatalogService implements ICmsCatalogService{
	
	@Resource
	private CmsCatalogMapper cmsCatalogMapper;

	@Override
	public List<CmsCatalog> pageList(CmsCatalog cmsCatalog) {
		return cmsCatalogMapper.pageList(cmsCatalog);
	}

	@Override
	public Integer add(CmsCatalog cmsCatalog) {
		return cmsCatalogMapper.add(cmsCatalog);
	}

	@Override
	public Integer delete(Integer id) {
		return cmsCatalogMapper.delete(id);
	}

	@Override
	public Integer confine(Integer id, Integer status) {
		return cmsCatalogMapper.confine(id, status);
	}

	@Override
	public Integer updata(CmsCatalog cmsCatalog) {
		return cmsCatalogMapper.updata(cmsCatalog);
	}

	@Override
	public List<Map<String,Object>> queryBycatalogName(String catalogName) {
		return cmsCatalogMapper.queryBycatalogName(catalogName);
	}

}