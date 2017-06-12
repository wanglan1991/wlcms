package com.ekt.cms.apiUser.controller;

import java.util.Date;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ekt.cms.account.entity.CmsAccount;
import com.ekt.cms.apiUser.entity.ApiUser;
import com.ekt.cms.apiUser.entity.CmsUserBusiness;
import com.ekt.cms.apiUser.service.IApiUserService;
import com.ekt.cms.common.BaseController;
import com.ekt.cms.common.entity.Result;
import com.ekt.cms.utils.CMSConstants;
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
		CmsAccount account = getCurrentAccount();
		if (account != null && account.getRole() != CMSConstants.ADMIN && account.getRole() != CMSConstants.ROOT) {

			return Result.getResults(apiUserService.getEktUserPermissionDetail2(account.getEktapiUserId(), userId));
		} else {
			return Result.getResults(apiUserService.getEktUserPermissionDetail(userId));
		}

	}
	
	/**
	 * 用于标记是否为真实用户
	 * @param ektUserId
	 * @param isReal
	 * @return
	 */
	@RequestMapping(value = "/isReal")
	@ResponseBody
	public Result isInternalUser(int ektUserId,int isReal) {
		Result result = Result.getResults();
		result.setResult(apiUserService.isRealUser(ektUserId, isReal==1?isReal:0));
		return result;
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

	/**
	 * 生成CMS账户
	 * 
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/generate/cmsAccount")
	@ResponseBody
	public Result generateCmsAccount(int userId, int roleId) {
		CmsAccount account = getCurrentAccount();
		if (account == null) {
			return Result.getResults(-1, "请重新登录后再尝试！");
		}
		CmsAccount apiUserAccount = new CmsAccount();
		apiUserAccount.setEktapiUserId(userId);
		apiUserAccount.setRole(roleId);
		if (account != null && account.getRole() != CMSConstants.ADMIN && account.getRole() != CMSConstants.ROOT) {
			apiUserAccount.setParentId(account.getId());
		}
		return Result.getResults(apiUserService.generateCmsAccount(apiUserAccount));

	}

	/**
	 * 根据用户id获取用户事物
	 * 
	 * @param cub
	 * @return
	 */
	@RequestMapping(value = "/business")
	@ResponseBody
	public Result getUserBusinessByUserId(CmsUserBusiness cub) {
		cub.setAcceptanceStatus(0);
		CmsUserBusiness ub = apiUserService.getUserBusiness(cub);// 获取最近一次
																	// 未受理的该用户的事务
		if (ub != null) {// 如果事务不为空就返回该事务
			return Result.getResults(1, ub);
		} else {// 如果未处理事务为空 就直接返回最近一次受理的记录
			cub.setAcceptanceStatus(1);
			return Result.getResults(1, apiUserService.getUserBusiness(cub));
		}

	}

	/**
	 * 用户事物处理action
	 * 
	 * @param cub
	 * @return
	 */
	@RequestMapping(value = "business/acceptance")
	@ResponseBody
	public Result updateUserBusiness(CmsUserBusiness cub) {
		CmsAccount account = getCurrentAccount();
		if (account == null) {
			return Result.getResults(-1, "请重新登录后再尝试！");
		} else {
			cub.setAcceptanceStatus(0);
			CmsUserBusiness userBusiness = apiUserService.getUserBusiness(cub);
			if (userBusiness != null) {
				cub.setAcceptanceStatus(1);
				cub.setAcceptanceAccountId(account.getId());
				cub.setAcceptanceTime(new Date());
				return Result.getResults(apiUserService.acceptanceUserBusiness(cub));
			} else {
				return Result.getResults(-1, "有人已经抢先你一步处理了！");
			}

		}

	}
	
	/**
	 * 根据用户Id获取当前用户的事务记录
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "business/list")
	@ResponseBody
	public Result getUserBusinessListByUserId(@RequestParam("userId")int userId){
		return Result.getResults(apiUserService.getUserBusinessListByUserId(userId));
	}

}
