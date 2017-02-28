package com.ekt.cms.apiUser.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Result;
import org.springframework.stereotype.Service;

import com.ekt.cms.apiUser.dao.ApiUserMapper;
import com.ekt.cms.apiUser.entity.ApiUser;
import com.ekt.cms.common.entity.TreeBean;

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

	@Override
	public List<ApiUser> listPage(ApiUser apiUser) {
		return apiUserMapper.listPage(apiUser);
	}

	@Override
	public int confine(int userId) {
		return apiUserMapper.confine(userId);
	}

	@Override
	public List<TreeBean> getEktUserPermissionDetail(int userId) {
		return apiUserMapper.getEktUserPermissionDetail(userId);
	}

	@Override
	public int delPermissionByUserId(int userId) {
		return apiUserMapper.delPermissionByUserId(userId);
	}

	@Override
	public int insertEktUserPermission(int userId, int dictEktUserPermissionId) {
		return apiUserMapper.insertEktUserPermission(userId, dictEktUserPermissionId);
	}

}
