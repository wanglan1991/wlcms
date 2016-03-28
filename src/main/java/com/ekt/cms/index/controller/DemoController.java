package com.ekt.cms.index.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/demo")
public class DemoController {
	
	/**
	 * 初始化分页页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/demoPage")
	public String demoPage(HttpServletRequest request,HttpServletResponse response) {
		System.out.println("初始化分页页面");
		return "demo/page";
	}
	
	/**
	 * 初始化表单页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/demoForm")
	public String demoForm(HttpServletRequest request,HttpServletResponse response) {
		System.out.println("初始化表单页面");
		return "demo/form";
	}
}
