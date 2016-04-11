<<<<<<< HEAD
package com.ekt.cms.menu.service;

import java.util.List;

import com.ekt.cms.menu.entity.CmsMenu;
import com.ekt.cms.utils.page.Pagination;

public interface ICmsMenuService {
	//add
	public int addCmsMenu(CmsMenu cmsMenu) throws Exception;
	
	//query by primary key
	public CmsMenu queryByKey(int key) throws Exception;
	
	//delete by primary key
	public int deleteByKey(int key) throws Exception;
	
	//update by primary key
	public int updateByKey(CmsMenu cmsMenu) throws Exception;
	
	//query by condition
	public List<CmsMenu> queryByCondition(CmsMenu cmsMenu)throws Exception;
	
	//query pagination list by condition
	public List<CmsMenu> listPage(CmsMenu cmsMenu, Pagination pagination)throws Exception;
	/**
	 * 根据角色查询菜单列表
	 * @return 菜单List
	 */
	public List<CmsMenu> getMenuListByRole(Integer roleId)throws Exception;
	
}
=======
package com.ekt.cms.menu.service;

import java.util.List;

import com.ekt.cms.menu.entity.CmsMenu;
import com.ekt.cms.utils.page.Pagination;

public interface ICmsMenuService {
	//add
	public int addCmsMenu(CmsMenu cmsMenu) throws Exception;
	
	//query by primary key
	public CmsMenu queryByKey(int key) throws Exception;
	
	//delete by primary key
	public int deleteByKey(int key) throws Exception;
	
	//update by primary key
	public int updateByKey(CmsMenu cmsMenu) throws Exception;
	
	//query by condition
	public List<CmsMenu> queryByCondition(CmsMenu cmsMenu);
	
	//query pagination list by condition
	public List<CmsMenu> listPage(CmsMenu cmsMenu, Pagination pagination);
}
>>>>>>> upstream/master
