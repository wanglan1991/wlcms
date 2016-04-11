package com.ekt.cms.menu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ekt.cms.menu.entity.CmsMenu;
import com.ekt.cms.utils.page.Pagination;

/**
 * CMS menu mapper author : chenxin create date : 2016.4.6
 * 
 * notice： 这是一个接口文件，mybatis3 只需要定义dao接口，不需要实现类， 会自动匹配，名字和mapper文件夹下面的 xml文件名字要一致
 **/
public interface CmsMenuMapper {

	// 使用主键删除
	int deleteByPrimaryKey(Integer id);

	// 插入对象，返回新插入数据的ID
	int insert(CmsMenu cmsMenu);

	// 根据主键查询对象
	CmsMenu selectByPrimaryKey(Integer id);

	// 更新
	int updateByPrimaryKey(CmsMenu cmsMenu);
	
	// 根据条件查询
    List<CmsMenu> queryByCondition(@Param("cmsMenu")CmsMenu cmsMenu);
	
	/**
	 * 分页查找
	 * 
	 * @param 传入带查询条件的对象参数
	 * @param 传入分页对象参数
	 * @return 返回包含对象的List
	 */
	List<CmsMenu> listPage(@Param("cmsMenu") CmsMenu cmsMenu, @Param("pagination") Pagination pagination);
	
	/**
	 * 
	 * @param roleId
	 * @return 根据角色id返回父级菜单
	 */
	List<CmsMenu> getParentMenuListByRole(@Param("roleId")Integer roleId);
	/**
	 * 
	 * @param roleId
	 * @return 根据角色id、父级菜单id获得父级菜单中的子菜单
	 */
	List<CmsMenu> getSonMenuListByRole(@Param("parentId")Integer parentId,@Param("roleId")Integer roleId);
	
	
}