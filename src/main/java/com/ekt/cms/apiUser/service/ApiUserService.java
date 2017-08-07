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
 * @version 创建时间：2016年6月22日 下午4:47:01 程序的简单说明
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
	public int insertEktUserPermission(int userId, int dictEktUserPermissionId, String expireTime) {
		return apiUserMapper.insertEktUserPermission(userId, dictEktUserPermissionId, expireTime);
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

	@Override
	public int updateCouponIdUseStatus(int couponId) {
		return apiUserMapper.updateCouponIdUseStatus(couponId);
	}

	@Override
	public List<TreeBean> getgiftCourseDetailByUserId(int userId) {
		return apiUserMapper.getgiftCourseDetailByUserId(userId);
	}

	@Override
	public int delGiftCourseByUserId(int userId) {
		return apiUserMapper.delGiftCourseByUserId(userId);
	}

	@Override
	public int insertGiftCourse(int userId, int id, int accountId, String expireTime) {
		return apiUserMapper.insertGiftCourse(userId, id, accountId, expireTime);
	}

	@Override
	public ApiUser getEktUserByTelephone(String telephone) {
		return apiUserMapper.getEktUserByTelephone(telephone);
	}
	
	
	public String codeReactor() {

		Boolean flag = true;
		int t = 0;
		while (flag) {
			t = (int) (Math.random() * 89999999) + 10000000;
			if (apiUserMapper.getUserByUserName(String.valueOf(t)) != null) {
				flag = true;
			} else {
				flag = false;
			}

		}
		return String.valueOf(t);
	}

	@Override
	public int addUser(ApiUser newUser) {
		return apiUserMapper.insertUsertBackId(newUser);
	}

	@Override
	public int insertExperience(int userId, int total, String remark) {
		return apiUserMapper.insertExperience(userId, total, remark);
	}

	@Override
	public ApiUser getUserById(int userId) {
		return apiUserMapper.getUserById(userId);
	}

}
