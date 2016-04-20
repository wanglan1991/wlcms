package com.ekt.cms.permission.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ekt.cms.permission.dao.CmsPermissionMapper;
import com.ekt.cms.permission.entity.CmsPermission;
/**
 * 
 * @author 王岚
 * 2016-04-20 15:14
 *
 */
//@Service("iPermissionService")
public class PermissionService implements IPermissionService {
//	@Resource
	private CmsPermissionMapper cmsPermissionMapper;

	@Override
	public List<CmsPermission> listPage(CmsPermission cmsPermission) {
		return cmsPermissionMapper.listPage(cmsPermission);
	}

	@Override
	public List<CmsPermission> queryPermissionByRoleId(Integer roleId) {
		return cmsPermissionMapper.queryPermissionByRoleId(roleId);
	}



}
