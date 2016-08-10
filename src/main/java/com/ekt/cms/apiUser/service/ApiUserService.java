package com.ekt.cms.apiUser.service;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Result;
import org.springframework.stereotype.Service;

import com.ekt.cms.apiUser.dao.ApiUserMapper;
import com.ekt.cms.apiUser.entity.ApiUser;

/** 
* @author wanglan 
* @email wanglan-TD@foxmail.com
* @version 创建时间：2016年6月22日 下午4:47:01 
* 程序的简单说明 
*/

@Service("apiUserService")
public class ApiUserService implements IApiUserService {
	
	@Resource
	private ApiUserMapper apiUserMapper;

	@Override
	public ApiUser getUserByUsername(String userName) {
		return apiUserMapper.getUserByUsername(userName);
	}

}
