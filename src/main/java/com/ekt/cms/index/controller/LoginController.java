package com.ekt.cms.index.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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

import com.ekt.cms.account.entity.CmsAccount;
import com.ekt.cms.account.service.CmsAccountService;
import com.ekt.cms.index.entity.ResultVO;
import com.ekt.cms.index.entity.User;
import com.ekt.cms.utils.Constants;

/****
 *   登录控制controller
 *   author ：  陈鑫 
 *   date ： 2016/4/13
 *  
*/

@Controller
@RequestMapping("/user")
public class LoginController {
	
	//注入账户service
	@Resource
	private CmsAccountService cmsAccountService;
	
	//跳转到首页登录页面
	@RequestMapping(value = "/login")
	public String login() {
		System.out.println("跳转到登录页面的方法！");
		return "user/login";
	}
	
	/**
	 * 退出登录状态
	 * @return
	 */
	@RequestMapping(value = "/exit")
	public String exit(){
		//销毁session
//		destroySession();
		SecurityUtils.getSubject().logout();
		return "user/login";
	}
	
	/**
	 * 		点击登录按钮，调用后台检查用户信息
	 * 		1、首先验证前台Form表单传入的参数是否合法
	 *   	2、调用shiro框架方法登录
	 * 
	*/
	@RequestMapping(value = "/check-login")
	public @ResponseBody ResultVO checklogin(@Valid @ModelAttribute User user, BindingResult bindingResult,
			HttpServletRequest request) {
		ResultVO resultVO = new ResultVO(true);
		
		//1、首先验证前台Form表单传入的参数是否合法,如果fieldErrors不为空表示有错误则返回bean类定义的错误信息
		List<FieldError> fieldErrors = bindingResult.getFieldErrors();
		if (fieldErrors != null && !fieldErrors.isEmpty()) {
			String errorMessage = fieldErrors.get(0).getDefaultMessage();
			resultVO.setOk(false);
			resultVO.setMsg(errorMessage);
			return resultVO;
		}
		// 验证登录
		UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(), user.getPassword());
		token.setRememberMe(user.isRememberMe());
		Subject subject = SecurityUtils.getSubject();
		try {
			//调用shiro登录方法
			subject.login(token);
		} catch (AuthenticationException e) {
			e.printStackTrace();
			resultVO.setOk(false);
			resultVO.setMsg("账号或者密码错误");
			return resultVO;
		}
		CmsAccount account = cmsAccountService.queryByUserName(user.getUserName());
		// session当前用户
		Session session = subject.getSession();
		session.setAttribute(Constants.DEFAULT_SESSION_ACCOUNT, account);
		return resultVO;
	}
}
