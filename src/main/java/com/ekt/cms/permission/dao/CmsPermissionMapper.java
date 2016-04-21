package com.ekt.cms.permission.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

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
	
	//查询所有权限
	public List<CmsPermission> selectAll();
	
	//查询permission list
	public List<CmsPermission> listPage(@Param("cmsPermission")CmsPermission cmsPermission);
	//获取类型list
	public List<CmsPermission> getTypeList() ;
	//获取父级pidList
	public List<CmsPermission> getPidList(@Param("type")int type);
	//停用启用
	public int confine(@Param("cmsPermission")CmsPermission cmsPermission);
	//根据id删除
	public int deleteCmsPermission(@Param("cmsPermissionId")int cmsPermissionId);
	//添加权限
	public int addPermission(@Param("cmsPermission")CmsPermission cmsPermission);
	


}