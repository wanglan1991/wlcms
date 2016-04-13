package com.ekt.cms.index.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ekt.cms.index.entity.ResultVO;
import com.ekt.cms.menu.service.CmsMenuService;
import com.ekt.cms.account.entity.CmsAccount;
import com.ekt.cms.account.service.CmsAccountService;
import com.ekt.cms.utils.Constants;

@Controller
@RequestMapping(value = "/index")
public class IndexController {

	@Resource
	private CmsAccountService cmsAccountService;
	@Resource
	private CmsMenuService cmsMenuService;


	@RequestMapping(value = "/toTable")
	public String toTable(HttpServletRequest request, HttpServletResponse response) {

		System.out.print("222");
		return "index/tables";
	}

	@RequestMapping(value = "/login")
	public String login(HttpServletRequest request, HttpServletResponse response) throws Exception {
		cmsMenuService.queryByKey(1);
		return "user/login";
	}

	@RequestMapping(value = "/index")
	public String toIndex(HttpServletRequest request, HttpServletResponse response) throws Exception {
		cmsMenuService.queryByKey(1);
		return "main/index";
	}

	@RequestMapping(value = "/check-login")
	public @ResponseBody ResultVO checklogin(@Valid @ModelAttribute CmsAccount cmsAccount, BindingResult bindingResult,
			HttpServletRequest request) {
		ResultVO resultVO = new ResultVO(true);
		List<FieldError> fieldErrors = bindingResult.getFieldErrors();
		if (fieldErrors != null && !fieldErrors.isEmpty()) {
			String errorMessage = fieldErrors.get(0).getDefaultMessage();
			resultVO.setOk(false);
			resultVO.setMsg(errorMessage);
			return resultVO;
		}
		// 验证登录
		UsernamePasswordToken token = new UsernamePasswordToken(cmsAccount.getUserName(), cmsAccount.getPassword());
		token.setRememberMe(cmsAccount.isRememberMe());
		Subject subject = SecurityUtils.getSubject();
		try {
			subject.login(token);

		} catch (AuthenticationException e) {
			e.printStackTrace();
			resultVO.setOk(false);
			resultVO.setMsg("账号或者密码错误");
			return resultVO;
		}
		List<CmsAccount> account = cmsAccountService.queryByCondition(cmsAccount);
		// session当前用户
		Session session = subject.getSession();
		session.setAttribute(Constants.DEFAULT_SESSION_ACCOUNT, account.get(0));
		return resultVO;
	}
}
