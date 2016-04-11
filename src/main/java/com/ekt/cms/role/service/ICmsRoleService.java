package com.ekt.cms.role.service;


import java.util.List;
import java.util.Map;

import com.ekt.cms.role.entity.CmsRole;

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
	 * 添加角色
	 * @param cmsRole角色
	 * @return 是否操作成功
	 */
	 Map<String,Object> addCmsRole(CmsRole cmsRole);
	 
	 /**
	  * 删除角色
	  * @param id
	  * @return 是否操作成功
	  */
	 Map<String,Object> deleteCmsRole(Integer id);
	 /**
	  * 更新角色
	  * @param CmsRole cmsRole
	  * @return 是否操作成功
	  */
	 Map<String,Object> updateCmsRole(CmsRole cmsRole);
}
