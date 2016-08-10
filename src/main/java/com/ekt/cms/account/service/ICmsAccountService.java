package com.ekt.cms.account.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.shiro.authz.SimpleAuthorizationInfo;

import com.ekt.cms.account.entity.CmsAccount;
import com.ekt.cms.exercise.entity.CmsAnswer;
import com.ekt.cms.utils.page.Pager;
import com.ekt.cms.utils.page.Pagination;
import com.ekt.cms.utils.pageHelper.PageBean;

public interface ICmsAccountService {
	
	public PageBean<CmsAccount> listPage(CmsAccount cmsAccount);
	
	public List<CmsAccount> queryByCondition(CmsAccount cmsAccount);

	public SimpleAuthorizationInfo getAccountRolePermission(int id);
	

	public CmsAccount selectByPrimaryKey(Integer id);

	/**
	 * 修改用户
	 * @param cmsAccount
	 * @return 操作是否成功
	 */
	public int update(CmsAccount cmsAccount);
	/**
	 * 根据id删除用户
	 * @param id
	 * @return 操作是否成功
	 */
	public int delete(int id);
	/**
	 * 添加用户
	 * @param cmsAccount
	 * @return 操作是否成功
	 */
	public int addAccount(CmsAccount cmsAccount);
	/**
	 * 根据用户名查询用户
	 * @param userName
	 * @return 用户
	 */
	public CmsAccount queryByUserName(String userName);
	/**
	 * 根据用户id启用或停用用户
	 * @param cmsAccount
	 * @return 是否成功
	 */
	public int confine(CmsAccount cmsAccount);
	/**
	 * 根据用户id修改密码
	 * @param id
	 * @return
	 */
	public int setPwd(CmsAccount cmsAccount);
	/**
	 * 修改用户信息
	 * @param cmsAccount
	 * @return
	 */
	public int updateAccount(CmsAccount cmsAccount);
	 /**
     * 获取某角色的所有用户
     * @param String
     * @return CmsAccount
     */
    public List<CmsAccount> listAccountByRole(String  role);
    
    

}

