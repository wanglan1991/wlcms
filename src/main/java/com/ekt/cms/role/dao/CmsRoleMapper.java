package com.ekt.cms.role.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ekt.cms.role.entity.CmsRole;
import com.ekt.cms.role.entity.Keyword;
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

	/**
	 * 修改用户
	 * @param cmsRole
	 * @return
	 */
	List<CmsRole> listPage(@Param("cmsRole")CmsRole cmsRole);
	/**
	 * 根据用户编码查询用户
	 * @param encoding
	 * @return用户
	 */
	public CmsRole getCmsRoleByEncoding(@Param("encoding")String encoding);
	
	/**
	 * 添加用户
	 * @param cmsRole
	 * @return 是否操作成功
	 */
	public int addCmsRole(@Param("cmsRole")CmsRole cmsRole);
	
	/**
	 * 修改角色状态
	 * @param cmsRole
	 * @return
	 */
	public int confine(@Param("cmsRole")CmsRole cmsRole);
	/**
	 * 根据用户id获取菜单
	 * @param roleId
	 * @return
	 */
	public int getMenuList(@Param("roleId")int roleId);	
	
	
	
	/**
	 * 根据角色查询父级菜单
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>>getParentMenuList(@Param("keyword")Keyword keyword);
	/**
	 * 根据角色父级菜单Id查询子菜单
	 * @param keword
	 * @return
	 */
	public List<Map<String,Object>> getSonMenuList(@Param("keyword")Keyword keyword);
	/**
	 * 根据角色Id 菜单Id查询权限
	 * @param keword
	 * @return
	 */
	public List<Map<String,Object>> getPermission(@Param("keyword")Keyword keyword);
	
	
	public List<Map<String,Object>> getParentTree();
	
	public List<Map<String,Object>> gitSonTree();
	

}
