package com.ekt.cms.user.service;

import java.util.List;

import org.apache.shiro.authz.SimpleAuthorizationInfo;

import com.ekt.cms.user.entity.CmsUser;

public interface ICmsUserService {
	
	public List<CmsUser> queryByCondition(CmsUser cmsuser);

	public SimpleAuthorizationInfo getAccountRolePermission(int id);
	
}
