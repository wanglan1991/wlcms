package com.ekt.cms.shiro;

import java.io.PrintWriter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import com.alibaba.fastjson.JSON;
import com.ekt.cms.utils.DataUtil;
import com.ekt.cms.utils.ThreadLocalUtil;
import com.ekt.cms.index.entity.ResultVO;

public class OnLineFilter extends FormAuthenticationFilter {

	private static final Logger logger = Logger.getLogger(OnLineFilter.class);

	private String noLoginUrl;

	public String getNoLoginUrl() {
		return noLoginUrl;
	}

	public void setNoLoginUrl(String noLoginUrl) {
		this.noLoginUrl = noLoginUrl;
	}

	@Override
	protected boolean isAccessAllowed (ServletRequest request, ServletResponse response, Object mappedValue){

		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		if (session != null) {

			if (!subject.isAuthenticated() && subject.isRemembered()) {
				System.out.println("状态认证");
				return true;
			}

			if (subject.isAuthenticated()) {
				System.out.println("---身份验证");
				return true;
			}
		}
		return false;
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String basePath = ThreadLocalUtil.getBasePath();
		boolean isAjax = DataUtil.isAjax(httpRequest);
		if (isAjax) {
			PrintWriter writer = httpResponse.getWriter();
			ResultVO resultVO = new ResultVO();
			resultVO.setOk(false);
			resultVO.setMsg("请登录");
			resultVO.setUrl(basePath + getNoLoginUrl());
			writer.print(resultVO);
			// writer.print(JSON.toJSONString(resultVO));
			return false;
		}

		httpResponse.sendRedirect(basePath + getNoLoginUrl());
		return false;
	}

}
