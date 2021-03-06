package com.ekt.cms.permission.service;
import java.util.List;
import java.util.Map;

import com.ekt.cms.permission.entity.CmsPermission;
import com.ekt.cms.utils.pageHelper.PageBean;
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
	public PageBean<CmsPermission> listPage(CmsPermission cmsPermission);
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
	/**
	 * 根据id停用或启用
	 * @param cmsPermission
	 * @return
	 */
	public int confine(CmsPermission cmsPermission);
	/**
	 * 根据Id删除
	 * @param id
	 * @return
	 */
	public int deleteCmsPermission(int id);
	/**
	 * 根据permissionID删除所有与该permissionID相关的权限赋值
	 * @param id
	 * @return
	 */
	public int deleteRolePermission(int id);
	/**
	 * 添加权限
	 * @param cmsPermission
	 * @return
	 */
	public  int addPermission(CmsPermission cmsPermission);
	
	
	/**
	 * 更新权限
	 * @param cmsPermission
	 * @return
	 */
	public int updatePermission(CmsPermission cmsPermission);
	
	
}
