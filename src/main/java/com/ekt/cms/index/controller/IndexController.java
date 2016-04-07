package com.ekt.cms.index.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ekt.cms.utils.freemarker.StaticSupportInfo;

@Controller
@RequestMapping(value="/index")
public class IndexController {
//	@RequestMapping(value = "/login")
//	public String login(HttpServletRequest request, Model model, String createHtml) {
//
//		// 如果页面需要静态化
//		if (createHtml != null && "true".equals(createHtml)) {
//			StaticSupportInfo staticSupportInfo = new StaticSupportInfo();
//			// 设置静态化文件名
//			staticSupportInfo.setTargetHtml("index.html");
//
//			// 以下为实现静态化处理结果回调函数，如果不关心处理结果可以不做这一步
//			staticSupportInfo.setStatusCallBack(new StaticSupportInfo.StatusCallBack() {
//				public void fail() {
//					System.out.println("静态化处理结果回调，静态化失败");
//				}
//
//				public void success() {
//					System.out.println("静态化处理结果回调，静态化成功");
//				}
//			});
//
//			// 将静态化信息支持对象放到Attribute中，注意key值不要写错
//			request.setAttribute("staticSupportInfo", staticSupportInfo);
//		}
//		return "index/login";
//	}
	
	@RequestMapping(value = "/doLogin")
	public String doLogin(HttpServletRequest request,HttpServletResponse response) {
		System.out.print("222");
		return "main/index";
	}
	
	@RequestMapping(value = "/toTable")
	public String toTable(HttpServletRequest request,HttpServletResponse response) {
		System.out.print("222");
		return "index/tables";
	}
	
	@RequestMapping(value = "/login")
	public String login(HttpServletRequest request,HttpServletResponse response) {
		System.out.print("222");
		return "user/login";
	}
	
}
