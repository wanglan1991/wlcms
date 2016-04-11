package com.ekt.cms.account.service;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authc.Account;

import com.ekt.cms.account.entity.CmsAccount;
/**
 * 
 * @author wanglan
 *
 */
public interface ICmsAccountsService {
	/**
	 * 查询用户
	 * @return 用户 
	 */
	CmsAccount findAccount(CmsAccount cmsAccount);
	/**
	 * 删除用户
	 * @return 是否操作成功
	 */
	Integer deleteById(Integer id);
	/**
	 * 修改用户
	 * @return 是否操作成功
	 */
	Integer updateById(CmsAccount cmsAccount);
	/**
	 * 添加用户
	 * @return Cms用户List
	 */
	List<Map<String,CmsAccount>> getAccountListByAccount(CmsAccount cmsAccount);
	/**
	 * 无参重载
	 * @return Cms用户list
	 */
	List<Map<String,CmsAccount>> getAccountListByAccount();
	/**
	 * 添加用户
	 * @return 是否操作成功
	 */
	Integer addAccount(CmsAccount cmsAccount);

}
