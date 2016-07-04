package com.ekt.cms.apiUser.service;

import com.ekt.cms.apiUser.entity.ApiUser;

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

}
