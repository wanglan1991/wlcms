package com.ekt.cms.role.service;


import java.util.List;
import java.util.Map;

import com.ekt.cms.role.entity.CmsRole;
import com.ekt.cms.utils.pageHelper.PageBean;

/**
 * 
 * @author wanglan
 *
 */
public interface ICmsRoleService {
	/**
	 * 根据角色ID查询CmsRole
	 * @return CmsRole角色对象
	 */
	CmsRole getCmsRoleById(Integer id);
	/**
	 * 获取角色集合
	 * @return 角色集合
	 */
	List<CmsRole> getCmsRoleList();
	
	 
	 /**
	  * 删除角色
	  * @param id
	  * @return 是否操作成功
	  */
	int deleteCmsRole(Integer id);
	 /**
	  * 更新角色
	  * @param CmsRole cmsRole
	  * @return 是否操作成功
	  */
	int updateCmsRole(CmsRole cmsRole);
	 
	 
	 
	 
	 
	 
	 
	 
	 /**
	  * 获取用户list
	  * @param cmsRole
	  * @return
	  */
	 
	 List<CmsRole> listPage(CmsRole cmsRole); 
	 /**
	  * 根据角色编码查询角色
	  * @param encoding
	  * @return 角色
	  */
	 CmsRole getCmsRoleByEncoding(String encoding);
	 /**
	  * 添加角色
	  * @param cmsRole
	  * @return是否操作成功
	  */
	 int  addCmsRole(CmsRole cmsRole);
	 
	 
	 /**
	  * 修改角色状态
	  * @param cmsRole
	  * @return
	  */
	 int confine(CmsRole cmsRole);
	 
	 /**
	  * 获取Tree
	  * @param cmsRole
	  * @return
	  */
	 List<Map<String,Object>> getTreeByRoleId(int roleId);
	 /**
	  * 根据角色Id删除所有权限
	  * @param roleId
	  * @return
	  */
	 int delPermissionByRoleId(int roleId);
	 
	 
	 
	 
	 int insertRolePermission(int permissionId,int roleId);
	 
	 
	 
	 
}
