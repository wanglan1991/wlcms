package com.ekt.cms.shiro;

import java.io.PrintWriter;
import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import com.ekt.cms.utils.Constants;
import com.ekt.cms.utils.DataUtil;
import com.ekt.cms.utils.ThreadLocalUtil;
import com.ekt.cms.account.entity.CmsAccount;
import com.ekt.cms.account.service.CmsAccountService;
import com.ekt.cms.index.entity.ResultVO;

public class OnLineFilter extends FormAuthenticationFilter  {

	private static final Logger logger = Logger.getLogger(OnLineFilter.class);

	@Resource
	private CmsAccountService cmsAccountService;

	private String noLoginUrl;

	public String getNoLoginUrl() {
		System.out.println(noLoginUrl);
		return noLoginUrl;

	}

	public void setNoLoginUrl(String noLoginUrl) {
		this.noLoginUrl = noLoginUrl;
	}

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
		
			Subject subject = SecurityUtils.getSubject();
			Session session = subject.getSession();
			CmsAccount cmsUser=(CmsAccount)session.getAttribute(Constants.DEFAULT_SESSION_ACCOUNT);
			if(cmsUser!=null||((HttpServletRequest) request).getServletPath().equals("/user/exit")){
				return true;
			}else{
				return false;
			}
			
		// 如果 isAuthenticated 为 false 证明不是登录过的，同时 isRememberd 为true
		// 证明是没登陆直接通过记住我功能进来的
	

//		if (subject.isAuthenticated()) {
//			return true;
//		}else{
//			if (session.getAttribute("account") == null) {
//				// 如果是空的则给session添加当前用户
//				Integer userId = (Integer) subject.getPrincipal();
//				CmsAccount account = cmsAccountService.selectByPrimaryKey(userId);
//				session.setAttribute(Constants.DEFAULT_SESSION_ACCOUNT, account);
//			}
//			return true;
//			
//		}

	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {

		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String basePath = ThreadLocalUtil.getBasePath();
		// String basePath = System.getProperty("web.root");
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
