package com.ekt.cms.role.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ekt.cms.role.dao.CmsRoleMapper;
import com.ekt.cms.role.entity.CmsRole;
import com.ekt.cms.role.entity.Keyword;
import com.mysql.fabric.xmlrpc.base.Array;

@Service("cmsRoleService")
public class CmsRoleService implements ICmsRoleService {
	@Resource
	private CmsRoleMapper cmsRoleMapper;

	@Override
	public CmsRole getCmsRoleById(Integer id) {

		return cmsRoleMapper.getCmsRoleById(id);
	}

	@Override
	public List<CmsRole> getCmsRoleList(int parentId) {
		CmsRole cmsRole = new CmsRole();
		cmsRole.setParentId(parentId);
		return cmsRoleMapper.getCmsRoleList(cmsRole);
	}

	@Override
	public int deleteCmsRole(Integer id) {
		return cmsRoleMapper.deleteCmsRole(id);
	}

	public int updateCmsRole(CmsRole cmsRole) {
		return cmsRoleMapper.updateCmsRole(cmsRole);
	}

	@Override
	public List<CmsRole> listPage(CmsRole cmsRole) {
		return cmsRoleMapper.listPage(cmsRole);
	}

	@Override
	public CmsRole getCmsRoleByEncoding(String encoding) {
		return cmsRoleMapper.getCmsRoleByEncoding(encoding);
	}

	@Override
	public int addCmsRole(CmsRole cmsRole) {
		return cmsRoleMapper.addCmsRole(cmsRole);
	}

	@Override
	public int confine(CmsRole cmsRole) {
		return cmsRoleMapper.confine(cmsRole);
	}

	@Override
	public List<Map<String, Object>> getTreeByRoleId(int roleId) {
		return cmsRoleMapper.getTreeByRoleId(roleId);
	}

	@Override
	public int delPermissionByRoleId(int roleId) {
		return cmsRoleMapper.delPermissionByRoleId(roleId);
	}

	@Override
	public  int insertRolePermission(int permissionId,int roleId){
		return cmsRoleMapper.insertRolePermission(permissionId, roleId);
	}

	@Override
	public List<Map<String, Object>> getTreeByRoleId2(int parentId, int roleId) {
		return cmsRoleMapper.getTreeByRoleId2(parentId, roleId);
	}

	

}
