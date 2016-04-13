package com.ekt.cms.role.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ekt.cms.role.entity.CmsRole;
/**
 * 角色Dao
 * 
 * @author wanglan
 *
 */
public interface CmsRoleMapper {
	/**
	 * 根据id查询角色
	 * @param id
	 * @return
	 */
	CmsRole getCmsRoleById(@Param("id")Integer id);
	/**
	 * 获取角色List
	 * @param cmsRole
	 * @return 角色
	 */
	List<CmsRole> getCmsRoleList(@Param("cmsRole")CmsRole cmsRole);
	/**
	 * 添加角色
	 * @param cmsRole
	 * @return 角色列表
	 */
	Integer addCmsRole(@Param("cmsRole")CmsRole cmsRole);
	/**
	 * 根据Encoding或者角色名称获取角色对象
	 * @param CmsRole
	 * @return 是否操作成功
	 */
	CmsRole getCmsRoleByNameOrEncoding(@Param("cmsRole")CmsRole CmsRole);
	/**
	 * 根据角色id删除角色
	 * @param id
	 * @return 角色
	 */
	Integer deleteCmsRole(@Param("id")Integer id);
	/**
	 * 根据角色id进行修改
	 * @param cmsRole
	 * @return  是否操作成功
	 */
	Integer updateCmsRole(@Param("cmsRole")CmsRole cmsRole);

}
