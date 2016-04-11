package com.ekt.cms.account.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ekt.cms.account.entity.CmsAccount;
import com.ekt.cms.account.service.ICmsAccountsService;
import com.ekt.cms.common.BaseController;
import com.ekt.cms.common.entity.PagerReqVO;



/**
 * cms账户管理
 * @author wanglan
 *
 */
@Controller
@RequestMapping(value="/account")
public class CmsAccountController extends BaseController {
	
	
	@Resource
	ICmsAccountsService iCmsAccountsService;
	
	//跳转到账户管理页面
	@RequestMapping(value="/manage")
	public String manager(){
		return "/user/manage";
	}
	@RequestMapping(value = "/list")
	@ResponseBody
	public  List<Map<String,CmsAccount>> getAccountList(PagerReqVO pagerReqVO){
		
		return iCmsAccountsService.getAccountListByAccount();	
	}
	

}
