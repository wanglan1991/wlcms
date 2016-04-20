package com.ekt.cms.permission.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ekt.cms.common.BaseController;
import com.ekt.cms.common.entity.Result;
import com.ekt.cms.permission.entity.CmsPermission;
import com.ekt.cms.role.entity.CmsRole;
import com.ekt.cms.utils.pageHelper.PageBean;
import com.ekt.cms.utils.pageHelper.PageContext;

/**
 * 权限类
 * @author wanglan
 * 2016-04-20
 *
 */
@Controller
@RequestMapping(value="/permission")
public class PermissionController extends BaseController{

	/**
	 * 返回权限首页
	 * @return
	 */
@RequestMapping(value="/manage")
public String permission(){
	return "main/permission/manage";
}	

/**
 * 加载permission列表
 * @return
 */
@RequestMapping(value="/listPage")
@ResponseBody
public PageBean<CmsPermission> getListPage(PageContext page, CmsPermission cmsPermission) {
	page.paging();

	return null;
}


	
	
}

