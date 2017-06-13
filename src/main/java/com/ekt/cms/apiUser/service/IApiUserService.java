package com.ekt.cms.apiUser.service;

import java.util.List;

import com.ekt.cms.apiUser.entity.ApiUser;
import com.ekt.cms.common.entity.TreeBean;

/** 
* @author wanglan 
* @email wanglan-TD@foxmail.com
* @version 创建时间：2016年6月22日 下午4:46:57 
* 程序的简单说明 
*/
public interface IApiUserService {
	
	/**
	 * 根据用户名获取用户对象
	 * @param userName
	 * @return
	 */
	ApiUser getUserByUsername(String username);

	
	/**
	 * 根据用户属性条件查询获取ektapi用户列表
	 * @param apiUser
	 * @return
	 */
	List<ApiUser> listPage(ApiUser apiUser);
	
	/**
	 * 根据用户id封停账号
	 * @param userId
	 */
	public int confine(int userId);
	
	
	/**
	 * 根据用户二课堂用户id获取用户的权限
	 * @param userId
	 * @return
	 */
	public List<TreeBean> getEktUserPermissionDetail(int userId);
	
	/**
	 * 根据用户id清空该用户的相关的所有二课堂相关权限
	 * @param userId
	 */
	public int delPermissionByUserId(int userId);

	/**
	 * 插入用户相关二课堂权限
	 * @param userId
	 * @param dictEktUserPermissionId
	 * @return
	 */
	public int insertEktUserPermission(int userId,int dictEktUserPermissionId);

}
