package com.ekt.cms.menu.controller;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ekt.cms.account.entity.CmsAccount;
import com.ekt.cms.common.BaseController;
import com.ekt.cms.menu.entity.CmsMenu;
import com.ekt.cms.menu.service.ICmsMenuService;
import com.ekt.cms.role.entity.CmsRole;
import com.ekt.cms.role.service.ICmsRoleService;

@Controller
@RequestMapping("/menu")
public class CmsMenuController extends BaseController {
	
	@Resource
	private ICmsMenuService cmsMenuService;
	@Resource
	private ICmsRoleService cmsRoleService;
	
	@RequestMapping("/list")
	@ResponseBody
	public List<CmsMenu> getMenu(HttpServletRequest request)throws Exception{
		CmsAccount account=new CmsAccount();
		account.setRole(2);
		CmsRole role=cmsRoleService.getCmsRoleById(account.getRole());
			return cmsMenuService.getMenuListByRole(role.getId());
	}
	
	
	

}
