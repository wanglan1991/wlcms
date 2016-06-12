package com.ekt.cms.permission.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ekt.cms.permission.dao.CmsPermissionMapper;
import com.ekt.cms.permission.entity.CmsPermission;
import com.ekt.cms.utils.pageHelper.PageBean;
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
	public PageBean<CmsPermission> listPage(CmsPermission cmsPermission) {
		return new PageBean<CmsPermission>(cmsPermissionMapper.listPage(cmsPermission));
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

	@Override
	public int confine(CmsPermission cmsPermission) {
		return cmsPermissionMapper.confine(cmsPermission);
	}

	@Override
	public int deleteCmsPermission(int id) {
		return cmsPermissionMapper.deleteCmsPermission(id);
	}

	@Override
	public int addPermission(CmsPermission cmsPermission) {
		return cmsPermissionMapper.addPermission(cmsPermission);
	}

	@Override
	public int deleteRolePermission(int id) {
		return cmsPermissionMapper.deleteRolePermission(id);
	}

	@Override
	public int updatePermission(CmsPermission cmsPermission) {
		return cmsPermissionMapper.updatePermission(cmsPermission);
	}



}
