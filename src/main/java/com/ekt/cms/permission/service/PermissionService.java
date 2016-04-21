package com.ekt.cms.permission.service;

import java.util.List;
import java.util.Map;

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
@Service("permissionService")
public class PermissionService implements IPermissionService {
	@Resource
	private CmsPermissionMapper cmsPermissionMapper;

	@Override
	public List<CmsPermission> listPage(CmsPermission cmsPermission) {
		return cmsPermissionMapper.listPage(cmsPermission);
	}

	@Override
	public List<CmsPermission> queryPermissionByRoleId(Integer roleId) {
		return cmsPermissionMapper.queryPermissionByRoleId(roleId);
	}

	@Override
	public List<CmsPermission> getTypeList() {
		return cmsPermissionMapper.getTypeList();
	}

	@Override
	public List<CmsPermission> getPidList(int type) {
		return cmsPermissionMapper.getPidList(type);
	}



}
