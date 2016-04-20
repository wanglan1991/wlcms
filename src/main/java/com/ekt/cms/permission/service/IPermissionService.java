package com.ekt.cms.permission.service;
import java.util.List;

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
	
	public List<CmsPermission> queryPermissionByRoleId(Integer roleId);
}
