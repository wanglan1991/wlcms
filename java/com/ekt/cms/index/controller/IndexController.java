package com.ekt.cms.index.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.ekt.cms.menu.service.CmsMenuService;
import com.ekt.cms.account.service.CmsAccountService;
import com.ekt.cms.common.BaseController;

@SuppressWarnings("rawtypes")
@Controller
@RequestMapping("/index")

public class IndexController extends BaseController {

	@Resource
	private CmsAccountService cmsAccountService;
	@Resource
	private CmsMenuService cmsMenuService;


    //跳转到登录页面
//  @RequestMapping("/login")
//  public String login() {
//  	System.out.println("经过登录页面");
//      return "user/login";`	
//  }

	/**
	 * 退出登录状态
	 * @return
	 */

	@RequestMapping(value = "/exit")
	public String exit(){
		//销毁session
		destroySession();
		return "user/login";
	}
	

	@RequestMapping(value = "/index")
	public String toIndex(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "main/index";
	}

}
