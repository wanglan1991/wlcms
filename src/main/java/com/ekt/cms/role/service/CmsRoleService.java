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
	public List<CmsRole> getCmsRoleList() {
		CmsRole cmsRole = new CmsRole();
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
	public List<Map<String, Object>> getTree() {
		List<Map<String, Object>> parentList = new ArrayList<Map<String, Object>>();
				parentList=cmsRoleMapper.getParentTree();
//				parentList.addAll(cmsRoleMapper.gitSonTree());
		return parentList;
	}
//	public List<Map<String, Object>> getTree(int roleId) {
//		List<Map<String, Object>> parentList = new ArrayList<Map<String, Object>>();
//		List<Map<String, Object>> sonList = new ArrayList<Map<String, Object>>();
//		List<Map<String, Object>> permissionList = new ArrayList<Map<String, Object>>();
//		Keyword keyword = new Keyword();
//		keyword.setRoleId(roleId);
//		parentList = cmsRoleMapper.getParentMenuList(keyword);
//		for (Map<String, Object> parentMap : parentList) {
//			keyword.setParentId(Integer.parseInt(parentMap.get("id").toString()));
//			sonList = cmsRoleMapper.getSonMenuList(keyword);
//			parentMap.put("sonList", sonList);
//			for (Map<String, Object> sonMap : sonList) {
//				keyword.setMenuId(Integer.parseInt(sonMap.get("id").toString()));
//				permissionList = cmsRoleMapper.getPermission(keyword);
//				sonMap.put("permissionList", permissionList);
//			}
//		}
//		return parentList;
//	}

}
