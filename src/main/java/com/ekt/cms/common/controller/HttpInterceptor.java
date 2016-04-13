package com.ekt.cms.common.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.Account;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.ekt.cms.account.entity.CmsAccount;
import com.ekt.cms.common.BaseController;
import com.ekt.cms.region.controller.RegionController;
import com.ekt.cms.utils.AuthPassport;
import com.ekt.cms.utils.Constants;

/**
 * 拦截器
 * @author wanglan
 *
 */
public class HttpInterceptor extends BaseController implements HandlerInterceptor{
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
					String url = request.getRequestURI();
					Subject subject = SecurityUtils.getSubject();
					Session session = subject.getSession();
					CmsAccount account = (CmsAccount) session.getAttribute(Constants.DEFAULT_SESSION_ACCOUNT);
					//如果session中的用户为null
					if(account == null){
						//如果用户请求包含url
						if(url.equals("/cms/index/login") || url.equals("/cms/index/check-login")){
							return true;
						}else{
							//否侧跳转至登录首页
							response.sendRedirect("/cms/index/login");  
							return true;
							}
						}					
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
