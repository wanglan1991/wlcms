package com.ekt.cms.common.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ekt.cms.common.entity.CmsDict;
/**
 * 2016-05-02
 * 
 * @author zhuyanqiong 
 */
public interface CmsDictMapper {
	/**
	 * 根据用户id删除
	 * 
	 * @param cmsDict
	 * @return
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * 插入
	 * @param cmsDict
	 * @return
	 */
	int insert(CmsDict dict);

	/**
	 * 根据用户id查询
	 * 
	 * @param cmsDict
	 * @return
	 */
	CmsDict selectByPrimaryKey(Integer id);

	/**
	 * 根据用户id更新
	 * 
	 * @param cmsDict
	 * @return
	 */
	int updateByPrimaryKey(CmsDict dict);

	/**
	 * 按条件查询
	 * 
	 * @param cmsDict
	 * @return
	 */
	public List<CmsDict> queryByCondition(@Param("CmsDict") CmsDict dict);

	/**
	 * 分页查询
	 * 
	 * @param cmsDict
	 * @return
	 */
	public List<CmsDict> listDictPage(@Param("CmsDict") CmsDict dict);

	/**
	 * 停用或启用用户 
	 * @param cmsDict
	 * @return
	 */
	int confine(@Param("CmsDict") CmsDict dict);
	/**
	 * 根据字典值查询
	 * @param value
	 * @return cmsDict
	 */
	CmsDict queryByDictName(String value);
	/**
	 * 查询所有的字典类型名称
	 * @param 
	 * @return 
	 */	
	List<CmsDict> queryTypeName();
	
	/**
	 * 获取名师列表
	 * @return
	 */
	public List<Map<String, Object>> famousTeacher();
	
	/**
	 * 上传习题时用到的字典查询接口
	 * @param value
	 * @param encoding
	 * @return
	 */
	public CmsDict exerciseQueryByDictNameAndEncoding(@Param("value")String value,@Param("encoding")String encoding);
	
}