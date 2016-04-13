
package com.ekt.cms.account.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ekt.cms.account.service.ICmsAccountService;
import com.ekt.cms.utils.AuthPassport;
@Controller
@RequestMapping(value="/account")
public class CmsAccountController {
	@Resource
	private ICmsAccountService cmsAccountService;
	
	@AuthPassport
	@RequestMapping(value="/manage")
	public String manage(){
		return "/user/manage";
		}
	
}