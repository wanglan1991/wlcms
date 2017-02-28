package com.ekt.cms.apiUser.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ekt.cms.apiUser.entity.ApiUser;
import com.ekt.cms.apiUser.service.IApiUserService;
import com.ekt.cms.common.BaseController;
import com.ekt.cms.common.entity.Result;
import com.ekt.cms.common.entity.TreeBean;
import com.ekt.cms.quintessence.entity.CmsQuintessence;
import com.ekt.cms.utils.pageHelper.PageBean;
import com.ekt.cms.utils.pageHelper.PageContext;

/**
 * @author wanglan
 * @email wanglan-TD@foxmail.com
 * @version 创建时间：2016年6月22日 下午4:45:08 程序的简单说明
 */

@Controller
@RequestMapping(value = "/user")
public class ApiUserController extends BaseController {

	@Resource
	private IApiUserService apiUserService;

	@RequestMapping("/manage")
	public String toVideoPage() {
		return "main/user/apiUserManage";
	}

	/**
	 * 根据用户属性条件查询获取ektapi用户列表
	 * 
	 * @param apiUser
	 * @return
	 */
	@RequestMapping(value = "/listPage")
	@ResponseBody
	public PageBean<ApiUser> listPage(PageContext page, ApiUser apiUser) {
		page.paging();
		return new PageBean<ApiUser>(apiUserService.listPage(apiUser));
	}

	/**
	 * 封停或启用用户
	 * 
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/confine")
	@ResponseBody
	public Result confine(int id) {
		Result result = Result.getResults();
		if (getCurrentAccount() == null) {
			result.setResult(-1);
		} else {
			result.setResult(apiUserService.confine(id));
		}
		return result;
	}

	/**
	 * 根据用户id获取二课堂用户权限
	 * 
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/treePermission")
	@ResponseBody
	public Result getEktUserPermissionDetail(int userId) {
		return Result.getResults(apiUserService.getEktUserPermissionDetail(userId));
	}

	/**
	 * 修改二课堂用户权限
	 * 
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/permission/edit")
	@ResponseBody
	@Transactional
	public Result editEktUserPermissionDetail(int userId, String ids) {
		int result = 1;
		apiUserService.delPermissionByUserId(userId);
		for (String id : ids.split(",")) {
			if (id != null && !id.equals("")) {
				result += apiUserService.insertEktUserPermission(userId, Integer.parseInt(id));
			}
		}

		return Result.getResults(result);
	}

}
