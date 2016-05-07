package com.ekt.cms.textbook.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ekt.cms.textbook.entity.CmsCatalog;

/**
 * @author wanglan 2016-05-05 目录DAO
 */
public interface CmsCatalogMapper {

	/**
	 * 可带参查询目录列表
	 * 
	 * @param catalog
	 * @return List<CmsCatalog>
	 */
	public List<CmsCatalog> pageList(@Param("catalog") CmsCatalog catalog);

	/**
	 * 根据id修改目录
	 * 
	 * @param cmsCatalog
	 * @return
	 */
	public Integer updata(@Param("catalog")CmsCatalog cmsCatalog);

	/**
	 * 根据id停用或启用目录
	 * 
	 * @param id
	 * @param status
	 * @return
	 */
	public Integer confine(@Param("id")Integer id, @Param("status")Integer status);

	/**
	 * 根据id删除目录
	 * 
	 * @param id
	 * @return
	 */
	public Integer delete(@Param("id") Integer id);

	/**
	 * 添加目录
	 * @param cmsCatalog
	 * @return
	 */
	public Integer add(CmsCatalog cmsCatalog);
	
	/**
	 * 根据目录名称查询目录
	 * @param catalogName
	 * @return
	 */
	public List<Map<String,Object>> queryBycatalogName(@Param("textbookId")Integer textbookId,@Param("catalogName")String catalogName);
	
	/**
	 * 根据目录级别查询父级List
	 * @param levelNo
	 * @param textbookId
	 * @return
	 */
	public List<Map<String, Object>> getCatalogParentList(@Param("levelNo")Integer levelNo,@Param("textbookId") Integer textbookId);
	
	/**
	 * 根据教材id删除目录
	 * @param parentId
	 * @return
	 */
	public Integer deleteByTextbookId(@Param("textbookId")Integer textbookId);
	
	/**
	 * 根据父级Id删除章节
	 * @param parentId
	 * @return
	 */
	public Integer deleteByParentId(@Param("parentId")Integer parentId) ;
	

}
