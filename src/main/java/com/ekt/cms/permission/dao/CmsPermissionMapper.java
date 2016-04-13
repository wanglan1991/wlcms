package com.ekt.cms.permission.dao;

import java.util.List;

import com.ekt.cms.permission.entity.CmsPermission;

/**
 * notice： 这是一个接口文件，mybatis3 只需要定义dao接口，不需要实现类， 会自动匹配，名字和mapper文件夹下面的 xml文件名字要一致
 **/
public interface CmsPermissionMapper {
	// 按主键查询 返回权限对象
	CmsPermission selectByPrimaryKey(Integer id);

	// 按主键删除
	int deleteByPrimaryKey(Integer id);

	// 插入
	int insert(CmsPermission cmsPermission);

	// 更新
	int updateByPrimaryKey(CmsPermission cmsPermission);

	// 根据角色ID查询
	public List<CmsPermission> queryPermissionByRoleId(Integer id);
}