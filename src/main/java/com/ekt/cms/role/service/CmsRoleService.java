package com.ekt.cms.role.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ekt.cms.role.dao.CmsRoleMapper;
import com.ekt.cms.role.entity.CmsRole;

@Service("cmsRoleService")
public class CmsRoleService implements ICmsRoleService{
	@Resource
	private  CmsRoleMapper cmsRoleMapper;

	@Override
	public CmsRole getCmsRoleById(Integer id) {
		
		return cmsRoleMapper.getCmsRoleById(id);
	}

	@Override
	public List<CmsRole> getCmsRoleList() {
		CmsRole cmsRole=new CmsRole();
		return cmsRoleMapper.getCmsRoleList(cmsRole);
	}



	@Override
	public Map<String, Object> deleteCmsRole(Integer id) {
		Map<String,Object>map=new HashMap<String,Object>();
		map.put("result",cmsRoleMapper.deleteCmsRole(id));
		return map;
	}
	public int updateCmsRole(CmsRole cmsRole){
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

}
