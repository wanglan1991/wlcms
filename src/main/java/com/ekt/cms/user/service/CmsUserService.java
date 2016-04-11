package com.ekt.cms.user.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.springframework.stereotype.Service;

import com.ekt.cms.permission.entity.CmsPermission;
import com.ekt.cms.permission.service.IPermissionService;
import com.ekt.cms.user.dao.CmsUserMapper;
import com.ekt.cms.user.entity.CmsUser;

@Service("cmsUserService")
public class CmsUserService implements ICmsUserService {
	@Resource
	private CmsUserMapper cmsUserMapper;
	@Resource
	private IPermissionService permissionService;

	@Override
	public List<CmsUser> queryByCondition(CmsUser cmsuser) {
		return cmsUserMapper.queryByCondition(cmsuser);
	}

	@Override
	// 根据用户ID查询所有权限
	public SimpleAuthorizationInfo getAccountRolePermission(int id) {
		SimpleAuthorizationInfo AuthorizationInfo = new SimpleAuthorizationInfo();
		// 根据userID查询到roleID
		CmsUser curUser = cmsUserMapper.selectByPrimaryKey(id);
		int roleId = curUser.getRole();
		// 根据roleID查询所有的权限和角色
		List<CmsPermission> cmsPermissions = (List<CmsPermission>) permissionService.queryPermissionByRoleId(roleId);
		curUser.setCmsPermissions(cmsPermissions);
		Set<String> permission = new HashSet<>();
		Set<String> roleEncoding = new HashSet<String>();
		if (cmsPermissions != null && cmsPermissions.size() > 0) {
			for (CmsPermission cmsPermission : cmsPermissions) {
				permission.add(cmsPermission.getKey());
				roleEncoding.add(cmsPermission.getEncoding());
			}
		}
		// 将权限信息和角色注入到SimpleAuthorizationInfo中
		AuthorizationInfo.addStringPermissions(permission);
		AuthorizationInfo.addRoles(roleEncoding);
		return AuthorizationInfo;
	}

}
