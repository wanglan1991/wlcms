package com.ekt.cms.menu.controller;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ekt.cms.common.BaseController;
import com.ekt.cms.menu.entity.CmsMenu;
import com.ekt.cms.menu.service.ICmsMenuService;
import com.ekt.cms.role.entity.CmsRole;
import com.ekt.cms.role.service.ICmsRoleService;
import com.ekt.cms.account.entity.CmsAccount;
import com.ekt.cms.utils.AuthPassport;
import com.ekt.cms.utils.Constants;

@SuppressWarnings("rawtypes")
@Controller
@RequestMapping("/menu")
public class CmsMenuController extends BaseController {
	
	@Resource
	private ICmsMenuService cmsMenuService;
	@Resource
	private ICmsRoleService cmsRoleService;
	
	@AuthPassport
	@RequestMapping("/list")
	@ResponseBody
	public List<CmsMenu>  getMenu(HttpServletRequest request)throws Exception{
		Subject curAccount=SecurityUtils.getSubject();
		Session session=curAccount.getSession();
		CmsAccount account=(CmsAccount)session.getAttribute(Constants.DEFAULT_SESSION_ACCOUNT);
		if (account.getRole()==null) {
			return null;
		}
			CmsRole role=cmsRoleService.getCmsRoleById(account.getRole());
			return cmsMenuService.getMenuListByRole(role.getId());
	}
	
	
	

}
