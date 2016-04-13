package com.ekt.cms.account.service;

import java.util.List;

import org.apache.shiro.authz.SimpleAuthorizationInfo;

import com.ekt.cms.account.entity.CmsAccount;

public interface ICmsAccountService {
	
	public List<CmsAccount> queryByCondition(CmsAccount cmsAccount);

	public SimpleAuthorizationInfo getAccountRolePermission(int id);
	
}

