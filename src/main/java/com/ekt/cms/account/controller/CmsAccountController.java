package com.ekt.cms.account.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ekt.cms.account.entity.CmsAccount;
import com.ekt.cms.account.service.ICmsAccountService;
import com.ekt.cms.common.controller.BaseController;
import com.ekt.cms.common.entity.Result;
import com.ekt.cms.role.service.ICmsRoleService;
import com.ekt.cms.utils.CMSConstants;
import com.ekt.cms.utils.EncryptUtil;
import com.ekt.cms.utils.pageHelper.PageBean;
import com.ekt.cms.utils.pageHelper.PageContext;

@Controller
@RequestMapping(value = "/account")
public class CmsAccountController extends BaseController {
	@Resource
	private ICmsAccountService cmsAccountService;

	@Resource
	ICmsRoleService cmsRoleService;
	
	

	@RequestMapping(value = "/manage")
	public String manage() {
		return "/user/userManage";
	}

	@RequestMapping("/list")
	@ResponseBody
	public PageBean<CmsAccount> accountList(PageContext page, CmsAccount cmsAccount) {
		CmsAccount account = getCurrentAccount();
		// 从session中获取用户如用户不为空并且用户名等于CMSROOT就显示自己的角色
		if (account != null && account.getUserName().equals("CMSROOT")) {
			cmsAccount.setId(account.getId());
		}
		if(account != null &&account.getRole()!=CMSConstants.ADMIN&&account.getRole()!=CMSConstants.ROOT){
			cmsAccount.setParentId(account.getId());
		}
		page.paging();
		return cmsAccountService.listPage(cmsAccount);
	}

	/**
	 * 返回用户列表
	 * 
	 * @return
	 */
	@RequestMapping("/roleList")
	@ResponseBody
	public Result getRoleList() {
		CmsAccount account = getCurrentAccount();
		Result result = Result.getResults();
		if(account != null &&account.getRole()!=CMSConstants.ADMIN&&account.getRole()!=CMSConstants.ROOT){
			result.setValue(cmsRoleService.getCmsRoleList(account.getRole()));
		}else{
			result.setValue(cmsRoleService.getCmsRoleList(0));
		}
		
		return result;

	}

	/**
	 * 修改角色
	 */
	@RequestMapping("/roleEdit")
	@ResponseBody
	public Result roleEdit(CmsAccount cmsAccount, HttpServletRequest request) {
		Result result = Result.getResults();
		result.setResult(cmsAccountService.update(cmsAccount));
		return result;
	}

	/**
	 * 删除用户
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Result accountDelete(@RequestParam("id") int id) {
		Result result = Result.getResults();
		if (getCurrentAccount() == null) {
			result.setResult(-1);
			result.setMsg("非法请求！");
			return result;
		}
		result.setResult(cmsAccountService.delete(id));
		return result;

	}

	/**
	 * 添加用户
	 * 
	 * @throws Exception
	 */
	@RequestMapping("/addAccount")
	@ResponseBody
	public Result addAccount(@Valid CmsAccount cmsAccount, BindingResult bindingResult) throws Exception {
		Result result = Result.getResults();
		List<FieldError> fieldErrors = bindingResult.getFieldErrors();
		if (fieldErrors != null && !fieldErrors.isEmpty()) {
			for (FieldError fieldError : fieldErrors) {
				result.setMsg(fieldError.getDefaultMessage());
			}
			result.setOk(false);
			return result;
		}
		CmsAccount currentAccount = getCurrentAccount();
		CmsAccount Account = cmsAccountService.queryByUserName(cmsAccount.getUserName());
		
		if (Account != null) {
			result.setMsg("用户名已经存在！");
			return result;
		}
		// 设置用户密码
		cmsAccount.setPassword(EncryptUtil.encryptPassword(cmsAccount.getPassword()));
		//如果不是管理员
		if(currentAccount != null &&currentAccount.getRole()!=CMSConstants.ADMIN&&currentAccount.getRole()!=CMSConstants.ROOT){
			cmsAccount.setParentId(currentAccount.getId());
		}
		
		// 添加用户
		result.setResult(cmsAccountService.addAccount(cmsAccount));

		return result;
	}

	/**
	 * 启用或停用用户
	 * 
	 * @param cmsAccount
	 * @return
	 */
	@RequestMapping("/confine")
	@ResponseBody
	public Result confine(CmsAccount cmsAccount) {
		Result result = Result.getResults();
		result.setResult(cmsAccountService.confine(cmsAccount));
		return result;

	}

	/**
	 * 重置密码
	 * 
	 * @param id
	 * @return 操作成功
	 */
	@RequestMapping("/resetPwd")
	@ResponseBody
	public Result resetPwd(CmsAccount cmsAccount) {
		Result result = Result.getResults();
		if (getCurrentAccount() == null) {
			result.setResult(-1);
			result.setMsg("非法请求！");
			return result;
		}
		cmsAccount.setPassword(EncryptUtil.encryptPassword("123456789"));
		result.setResult(cmsAccountService.setPwd(cmsAccount));
		return result;
	}

	/**
	 * 编辑用户
	 * 
	 * @param cmsAccount
	 * @return
	 */
	@RequestMapping("/editAccount")
	@ResponseBody
	public Object EditAccount(CmsAccount cmsAccount) {
		Result result = Result.getResults();
		result.setResult(cmsAccountService.updateAccount(cmsAccount));
		return result;

	}

	/**
	 * 批量删除用户
	 * 
	 * @param arr
	 * @return
	 */
	@RequestMapping("/deletes")
	@ResponseBody
	public Result deletes(@RequestParam("ids") String ids) {
		Result result = Result.getResults();
		if (getCurrentAccount() == null) {
			result.setResult(-1);
			result.setMsg("非法请求！");
			return result;
		}
		String[] arr = ids.split(",");
		int total = 0;
		for (int i = 0; i < arr.length; i++) {
			total += cmsAccountService.delete(Integer.parseInt(arr[i].toString()));
		}
		result.setResult(total);
		return result;
	}

	/**
	 * 根据角色查询用户
	 * 
	 * @param role
	 * @return
	 */
	@RequestMapping("/listAccountByRole")
	@ResponseBody
	public Object listAccount(@RequestParam("role") String role) {
		Result result = Result.getResults();
		result.setValue(cmsAccountService.listAccountByRole(role));
		return result;

	}

	/**
	 * 验证密码
	 * 
	 * @param password
	 * @return
	 */
	@RequestMapping("/validPassword")
	@ResponseBody
	public Result validPassword(@RequestParam("password") String password) {
		CmsAccount account = cmsAccountService.selectByPrimaryKey(getCurrentAccount().getId());
		String pwd = account.getPassword();
		if (!EncryptUtil.checkPassword( pwd,password)) {
			return Result.getResults(-1, "密码错误请重新验证！");
		} else {
			return Result.getResults(1, "请输入新密码");
		}

	}

	/**
	 * 修改密码
	 * 
	 * @param password
	 * @return
	 */
	@RequestMapping("/updatePassword")
	@ResponseBody
	public Result updatePassword(@RequestParam("password") String password) {
		String md5Pwd = EncryptUtil.encryptPassword(password);
		CmsAccount cmsAccount = new CmsAccount(getCurrentAccount().getId(), md5Pwd);
		int count = cmsAccountService.setPwd(cmsAccount);
		SecurityUtils.getSubject(); 
//		user.setPassword(md5Pwd);
		if (count > 0) {
			return Result.getResults(1, "密码修改成功！");
		} else {
			return Result.getResults(-1, "密码修改失败!");
		}

	}
	
	/**
	 * 获取个人信息
	 * @return
	 */
	@RequestMapping("/info")
	@ResponseBody
	public Result getAccountInfo(){
		return Result.getResults(getCurrentAccount());
	}
	
	
	/**
	 * 修改个人部分信息
	 */
	@RequestMapping("/updateAccountInfo")
	@ResponseBody
	public Result updateAccountInfo(CmsAccount account){
		account.setId(getCurrentAccount().getId());
		int result = cmsAccountService.updateAccount(account);
		return Result.getResults(result>0?1:0);
	}
	
	

}