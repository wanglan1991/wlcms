package com.ekt.cms.apiUser.dao;

import org.apache.ibatis.annotations.Param;

import com.ekt.cms.apiUser.entity.ApiUser;


/** 
* @author wanglan 
* @email wanglan-TD@foxmail.com
* @version 创建时间：2016年6月22日 下午4:45:58 
* 程序的简单说明 
*/
public interface ApiUserMapper {
	ApiUser getUserByUsername(@Param("userName")String userName);

}
