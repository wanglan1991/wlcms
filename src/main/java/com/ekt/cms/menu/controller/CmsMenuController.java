package com.ekt.cms.menu.controller;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ekt.cms.common.BaseController;
import com.ekt.cms.menu.entity.CmsMenu;
import com.ekt.cms.menu.service.ICmsMenuService;
import com.ekt.cms.role.entity.CmsRole;
import com.ekt.cms.role.service.ICmsRoleService;
import com.ekt.cms.account.entity.CmsAccount;
import com.ekt.cms.utils.Constants;

@SuppressWarnings("rawtypes")
@Controller
@RequestMapping("/menu")
public class CmsMenuController extends BaseController {
	
	@Resource
	private ICmsMenuService cmsMenuService;
	@Resource
	private ICmsRoleService cmsRoleService;
	
	@RequestMapping("/list")
	@ResponseBody
	public List<CmsMenu>  getMenu(HttpServletRequest request)throws Exception{
		CmsAccount account=(CmsAccount)request.getSession().getAttribute(Constants.DEFAULT_SESSION_ACCOUNT);	
		if (account.getRole()==null) {
			return null;
		}
			CmsRole role=cmsRoleService.getCmsRoleById(account.getRole());
			return cmsMenuService.getMenuListByRole(role.getId());
	}
	
	
	

}
