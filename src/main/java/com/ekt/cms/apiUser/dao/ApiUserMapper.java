package com.ekt.cms.apiUser.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ekt.cms.account.entity.CmsAccount;
import com.ekt.cms.apiUser.entity.ApiUser;
import com.ekt.cms.apiUser.entity.CmsUserBusiness;
import com.ekt.cms.common.entity.TreeBean;


/** 
* @author wanglan 
* @email wanglan-TD@foxmail.com
* @version 创建时间：2016年6月22日 下午4:45:58 
* 程序的简单说明 
*/
public interface ApiUserMapper {
	ApiUser getUserByUsername(@Param("userName")String userName);

	
	/**
	 * 根据用户属性条件查询获取ektapi用户列表
	 * @param apiUser
	 * @return
	 */
	List<ApiUser> listPage(@Param("user")ApiUser user);


	/**
	 * 根据用户id封停账号
	 * @param userId
	 */
	public int confine(int userId);
	
	/**
	 * 根据用户id获取二课堂用户权限
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
	public int insertEktUserPermission(@Param("userId")int userId,@Param("dictPermissionId")int dictPermissionId);
	
	/**
	 * 生成CMS用户
	 * @param userId
	 * @param roleId 
	 * @return
	 */
	public int generateCmsAccountByUserId(@Param("account")CmsAccount account);
	
	/**
	 * 根据用户id清空该用户的相关的所有二课堂相关权限
	 * @param userId
	 */
	public List<TreeBean> getEktUserPermissionDetail2(@Param("parentId")int parentId,@Param("userId")int userId);
	
	/**
	 * 根据用户对象获取用户最近一次代办事物
	 * @param cub
	 * @return
	 */
	public CmsUserBusiness getUserBusiness(CmsUserBusiness cub);
	
	
	/**
	 * 受理用户提交的事务
	 * @param cub
	 * @return
	 */
	public int acceptanceUserBusiness(CmsUserBusiness cub);
	
	/**
	 * 根据用户id获取用户提交事务的记录
	 * @param userId
	 * @return
	 */
	public List<CmsUserBusiness> getUserBusinessListByUserId(int userId);
	

	/**
	 * 标记是否为真实用户 1内部用户，0真实用户
	 * @param userId
	 * @param isReal
	 * @return
	 */
	public int isRealUser(@Param("userId")int userId,@Param("isReal")int isReal);

}
