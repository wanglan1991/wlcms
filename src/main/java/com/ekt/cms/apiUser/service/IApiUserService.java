package com.ekt.cms.apiUser.service;

import java.util.List;
import com.ekt.cms.account.entity.CmsAccount;
import com.ekt.cms.apiUser.entity.ApiUser;
import com.ekt.cms.apiUser.entity.CmsUserBusiness;
import com.ekt.cms.common.entity.TreeBean;

/**
 * @author wanglan
 * @email wanglan-TD@foxmail.com
 * @version 创建时间：2016年6月22日 下午4:46:57 程序的简单说明
 */
public interface IApiUserService {

	/**
	 * 根据用户名获取用户对象
	 * 
	 * @param userName
	 * @return
	 */
	ApiUser getUserByUsername(String username);
	
	
	/**
	 * 根据用户ID获取用户对象
	 * @param userId
	 * @return
	 */
	ApiUser getUserById(int userId);

	/**
	 * 根据用户属性条件查询获取ektapi用户列表
	 * 
	 * @param apiUser
	 * @return
	 */
	List<ApiUser> listPage(ApiUser apiUser);

	/**
	 * 根据用户id封停账号
	 * 
	 * @param userId
	 */
	public int confine(int userId);

	/**
	 * 根据用户二课堂用户id获取用户的权限
	 * 
	 * @param userId
	 * @return
	 */
	public List<TreeBean> getEktUserPermissionDetail(int userId);

	/**
	 * 1.根据cms登录用户获取permisson模板
	 * 
	 * @param userId
	 * @return
	 */
	public List<TreeBean> getEktUserPermissionDetail2(int parentId, int userId);

	/**
	 * 根据用户id清空该用户的相关的所有二课堂相关权限
	 * 
	 * @param userId
	 */
	public int delPermissionByUserId(int userId);

	/**
	 * 插入用户相关二课堂权限
	 * 
	 * @param userId
	 * @param dictEktUserPermissionId
	 * @param string
	 * @return
	 */
	public int insertEktUserPermission(int userId, int dictEktUserPermissionId, String expireTime);

	/**
	 * 生成CMS用户
	 * 
	 * @param userId
	 * @param roleId
	 * @return
	 */
	public int generateCmsAccount(CmsAccount account);

	/**
	 * 根据用户对象获取用户最近一次代办事物
	 * 
	 * @param cub
	 * @return
	 */
	public CmsUserBusiness getUserBusiness(CmsUserBusiness cub);

	/**
	 * 受理用户提交的事务
	 * 
	 * @param cub
	 * @return
	 */
	public int acceptanceUserBusiness(CmsUserBusiness cub);

	/**
	 * 根据用户id获取用户提交事务的记录
	 * 
	 * @param userId
	 * @return
	 */
	public List<CmsUserBusiness> getUserBusinessListByUserId(int userId);

	/**
	 * 标记是否为真实用户 1内部用户，0真实用户
	 * 
	 * @param userId
	 * @param isReal
	 * @return
	 */
	public int isRealUser(int userId, int isReal);

	/**
	 * 根据CouponId修改面试用劵的状态
	 * 
	 * @param couponId
	 * @return
	 */
	public int updateCouponIdUseStatus(int couponId);

	/**
	 * 根据用户id获取赠送的课程列表包含未赠送的
	 * 
	 * @param userId
	 * @return
	 */
	public List<TreeBean> getgiftCourseDetailByUserId(int userId);

	/**
	 * 根据用户id删除赠送的课程
	 * 
	 * @param userId
	 * @return
	 */
	public int delGiftCourseByUserId(int userId);

	/**
	 * 插入赠送的选课
	 * 
	 * @param userId
	 * @param id
	 * @param expireTime
	 * @return
	 */
	int insertGiftCourse(int userId, int id, int accountId, String expireTime);

	/**
	 * 根据手机号码获取用户对象
	 * @param telephone
	 * @return
	 */
	public ApiUser getEktUserByTelephone(String telephone);
	
	
	
	/**
	 * 8位账号生产机
	 * 
	 * @return 8位数字纯字符串
	 */
	public String codeReactor();

	/**
	 * 添加用户
	 * @param newUser
	 * @return
	 */
	int addUser(ApiUser newUser);
	
	/**
	 * 插入经验
	 * @param id
	 * @param i
	 * @param string
	 * @return
	 */
	int insertExperience(int userId, int total, String remark);

}
