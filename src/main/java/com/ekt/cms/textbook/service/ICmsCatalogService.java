package com.ekt.cms.textbook.service;

import java.util.List;
import java.util.Map;

import com.ekt.cms.textbook.entity.CmsCatalog;

/**
 * 
 * @author wanglan 2016 -05-05 目录接口
 */
public interface ICmsCatalogService {
	
	/**
	 * 可带参查询目录List
	 * @param msCatalog
	 * @return List<CmsCatalog>
	 */
	List<CmsCatalog> pageList(CmsCatalog msCatalog);
	
	/**
	 *添加目录 并返回自增id
	 * @param cmsCatalog
	 * @return
	 */
	Integer add(CmsCatalog cmsCatalog);
	
	/**
	 * 根据id删除目录
	 * @param id
	 * @return
	 */
	Integer delete(Integer id);
	
	/**
	 * 根据id启用或停用目录
	 * @param id
	 * @param status
	 * @return
	 */
	Integer confine(Integer id,Integer status);
	
	/**
	 *根据id修改目录信息 
	 * @param cmsCatalog
	 * @return
	 */
	Integer updata(CmsCatalog cmsCatalog);
	
	/**
	 * 根据目录名称查询目录对象
	 * @param catalogName
	 * @return
	 */
	List<Map<String,Object>> queryBycatalogName(String catalogName);

}