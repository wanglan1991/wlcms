package com.ekt.cms.wechat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ekt.cms.common.BaseController;

/**
 * 微信公众号管理控制器
 * @author wanglan	
 * 2016-11-29
 */

@Controller
public class WechatManagerController extends BaseController {
	
	@RequestMapping("/wechat/manager")
	public ModelAndView wechatManager(){
		return new ModelAndView("main/wechat/wechatManage");
	}
	
	
	
	
	

}
