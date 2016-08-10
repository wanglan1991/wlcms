package com.ekt.cms.apiUser.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ekt.cms.apiUser.entity.ApiUser;
import com.ekt.cms.apiUser.service.IApiUserService;
import com.ekt.cms.common.entity.Result;

/** 
* @author wanglan 
* @email wanglan-TD@foxmail.com
* @version 创建时间：2016年6月22日 下午4:45:08 
* 程序的简单说明 
*/

@Controller
@RequestMapping(value ="/user")
public class ApiUserController {
	
	@Resource
	private IApiUserService apiUserService;
	
	@RequestMapping("/getUser")
	@ResponseBody
	public Result getUserByName(@RequestParam("username")String username){
		ApiUser apiUser =apiUserService.getUserByUsername(username);
		Result result= Result.getResults();
		result.setResult(apiUser==null?0:1);
		result.setValue(apiUser);
		return result;
	}

}
