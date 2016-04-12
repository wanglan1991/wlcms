package com.ekt.cms.permission.service;

import java.util.List;

import com.ekt.cms.permission.entity.CmsPermission;

public interface IPermissionService {
	public List<CmsPermission> queryPermissionByRoleId(Integer roleId);
}
