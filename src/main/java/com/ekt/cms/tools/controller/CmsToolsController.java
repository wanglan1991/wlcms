package com.ekt.cms.tools.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;




@Controller
@RequestMapping(value ="/tools")
public class CmsToolsController {
	/**
	 * 每日精选管理页面
	 * @return
	 */
	@RequestMapping(value="/manager")
	public String manager(){		
		return "main/tools/toolsManage";	
	}

}
