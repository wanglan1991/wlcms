package com.ekt.cms.account.service;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.SimpleAuthorizationInfo;

import com.ekt.cms.account.entity.CmsAccount;
import com.ekt.cms.utils.page.Pager;
import com.ekt.cms.utils.page.Pagination;

public interface ICmsAccountService {
	
	public List<CmsAccount> listPage(Pagination pagination,CmsAccount cmsAccount);
	public List<CmsAccount> queryByCondition(CmsAccount cmsAccount);

	public SimpleAuthorizationInfo getAccountRolePermission(int id);
	
	/**
	 * 修改用户
	 * @param cmsAccount
	 * @return
	 */
	public int update(CmsAccount cmsAccount);
	/**
	 * 删除用户
	 * @param id
	 * @return
	 */
	public int delete(int id);
	/**
	 * 添加用户
	 * @param cmsAccount
	 * @return
	 */
	public int addAccount(CmsAccount cmsAccount);
}

