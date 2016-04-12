package com.ekt.cms.permission.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ekt.cms.permission.dao.CmsPermissionMapper;
import com.ekt.cms.permission.entity.CmsPermission;

@Service("permissionService")
public class PermissionService implements IPermissionService {
	@Resource
	private CmsPermissionMapper cmsPermissionMapper;

	// 根据角色ID查询所有权限
	@Override
	public List<CmsPermission> queryPermissionByRoleId(Integer roleId) {
		// TODO Auto-generated method stub
		return cmsPermissionMapper.queryPermissionByRoleId(roleId);
	}

}
