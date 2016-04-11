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
	public Map<String,Object> addCmsRole(CmsRole cmsRole) {
		Map<String,Object>map=new HashMap<String,Object>();
		CmsRole role=cmsRoleMapper.getCmsRoleByNameOrEncoding(cmsRole);
		if(role!=null){
			map.put("msg", "角色名称或角色编码已存在,无法进行提交！");
			map.put("result", 0);
		}else{
			map.put("result",cmsRoleMapper.addCmsRole(cmsRole));	
		}
		 return map;
	}

	@Override
	public Map<String, Object> deleteCmsRole(Integer id) {
		Map<String,Object>map=new HashMap<String,Object>();
		map.put("result",cmsRoleMapper.deleteCmsRole(id));
		return map;
	}
	public Map<String,Object> updateCmsRole(CmsRole cmsRole){
		Map<String,Object>map=new HashMap<String,Object>();
		CmsRole role=cmsRoleMapper.getCmsRoleByNameOrEncoding(cmsRole);
		if(null!=role){
			map.put("msg", "角色名称或角色编码已存在，无法进行提交！");
			map.put("result", 0);
		}else{
			map.put("result", cmsRoleMapper.updateCmsRole(cmsRole));
		}
		 return map;
	}

}
