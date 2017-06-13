package com.ekt.cms.apiUser.service;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.ekt.cms.account.entity.CmsAccount;
import com.ekt.cms.apiUser.dao.ApiUserMapper;
import com.ekt.cms.apiUser.entity.ApiUser;
import com.ekt.cms.apiUser.entity.CmsUserBusiness;
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

	@Override
	public int generateCmsAccount(CmsAccount account) {
		return apiUserMapper.generateCmsAccountByUserId(account);
	}

	@Override
	public List<TreeBean> getEktUserPermissionDetail2(int parentId, int userId) {
		return apiUserMapper.getEktUserPermissionDetail2(parentId, userId);
	}

	@Override
	public CmsUserBusiness getUserBusiness(CmsUserBusiness cub) {
		return apiUserMapper.getUserBusiness(cub);
	}

	@Override
	public int acceptanceUserBusiness(CmsUserBusiness cub) {
		return apiUserMapper.acceptanceUserBusiness(cub);
	}

	@Override
	public List<CmsUserBusiness> getUserBusinessListByUserId(int userId) {
		return apiUserMapper.getUserBusinessListByUserId(userId);
	}

	@Override
	public int isRealUser(int userId, int isReal) {
		return apiUserMapper.isRealUser(userId, isReal);
	}

}
