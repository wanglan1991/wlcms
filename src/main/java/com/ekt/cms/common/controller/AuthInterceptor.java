package com.ekt.cms.common.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class AuthInterceptor implements HandlerInterceptor  {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
//		response.setHeader("Access-Control-Allow-Origin", "*");// 允许任意域名发起的跨域请求
//		response.setHeader("Access-Control-Allow-Methods", "*");
////		response.setHeader("Access-Control-Allow-Headers", "*");
//		response.setHeader("Access-Control-Allow-Headers", "X-Requested-With,X_Requested_With");
//		response.setHeader("P3P", "CP=CAO PSA OUR");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
//		System.out.println(request.getRequestURI());
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
	}

}
