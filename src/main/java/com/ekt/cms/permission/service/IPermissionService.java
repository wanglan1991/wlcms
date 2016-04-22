package com.ekt.cms.permission.service;
import java.util.List;
import java.util.Map;

import com.ekt.cms.permission.entity.CmsPermission;
/**
 * 
 * @author 王岚
 *2016-04-20 15：14
 */
public interface IPermissionService {
	/**
	 * 根据菜单数据查询菜单
	 * @param cmsPermission
	 * @return
	 */
	public List<CmsPermission> listPage(CmsPermission cmsPermission);
	/**
	 * 根据角色名称查询权限
	 * @param roleId
	 * @return
	 */
	public List<CmsPermission> queryPermissionByRoleId(Integer roleId);
	
	/**
	 *获取类型List
	 * @return
	 */
	public List<CmsPermission> getTypeList();
	
	/**
	 * 获取父级id
	 * @param type
	 * @return
	 */
	public List<CmsPermission> getPidList(int type);
	
}
