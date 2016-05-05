package com.ekt.cms.account.controller;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ekt.cms.account.entity.CmsAccount;
import com.ekt.cms.account.service.ICmsAccountService;
import com.ekt.cms.common.BaseController;
import com.ekt.cms.common.entity.Result;
import com.ekt.cms.role.service.ICmsRoleService;
import com.ekt.cms.utils.Md5Utils;
import com.ekt.cms.utils.pageHelper.PageBean;
import com.ekt.cms.utils.pageHelper.PageContext;
@Controller
@RequestMapping(value="/account")
public class CmsAccountController extends BaseController {
	@Resource
	private ICmsAccountService cmsAccountService;
	
	@Resource ICmsRoleService cmsRoleService;
	
	
	@RequestMapping(value="/manage")
	public String manage(){
		return "/user/manage";
		}

	@RequestMapping("/list")
	@ResponseBody
	public PageBean<CmsAccount> accountList(PageContext page ,CmsAccount cmsAccount){
		CmsAccount account=getCurrentAccount();
		//从session中获取用户如用户不为空并且用户名等于CMSROOT就显示自己的角色
		if(account!=null&&account.getUserName().equals("CMSROOT")){
			cmsAccount.setId(account.getId());
		}
		page.paging();
		return cmsAccountService.listPage(cmsAccount);
	}
	
	/**
	 * 返回用户列表
	 * @return
	 */
	@RequestMapping("/roleList")
	@ResponseBody
	public Result getRoleList(){
		Result result=Result.getResults();
		result.setValue(cmsRoleService.getCmsRoleList());
		return result;
		
	}
	/** 
	 * 修改角色
	 */
	@RequestMapping("/roleEdit")
	@ResponseBody
	public Result roleEdit(CmsAccount cmsAccount ,HttpServletRequest request){
			Result result=Result.getResults();
			result.setResult(cmsAccountService.update(cmsAccount));
		return result;
	}
	/**
	 * 删除用户
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Result accountDelete(@RequestParam("id") int id){
		Result result=Result.getResults();
		if(getCurrentAccount()==null){
			result.setResult(-1);
			result.setMsg("非法请求！");
			return result;
		}
		result.setResult(cmsAccountService.delete(id));
		return result;
		
	}
	/**
	 * 添加用户
	 * @throws Exception 
	 */
	@RequestMapping("/addAccount")
	@ResponseBody
	public Result addAccount(@Valid CmsAccount cmsAccount, BindingResult bindingResult) throws Exception {
			Result result=Result.getResults();
			CmsAccount Account = cmsAccountService.queryByUserName(cmsAccount.getUserName());
			List<FieldError> fieldErrors = bindingResult.getFieldErrors();
			if (fieldErrors != null && !fieldErrors.isEmpty()) {
				for(FieldError fieldError:fieldErrors){
					result.setMsg(fieldError.getDefaultMessage());
				}
				result.setOk(false);
				return result;
			}
			if(Account != null){
				result.setMsg("用户名已经存在！");
				return result;
			}
			//设置用户密码
			cmsAccount.setPassword(Md5Utils.getMd5Encode(cmsAccount.getPassword()));
			//添加用户
			result.setResult(cmsAccountService.addAccount(cmsAccount));
			
		return result;
	}
	
	/**
	 * 启用或停用用户
	 * @param cmsAccount
	 * @return 
	 */
	@RequestMapping("/confine")
	@ResponseBody
	public Result confine(CmsAccount cmsAccount){
		Result result =new Result();
		result.setResult(cmsAccountService.confine(cmsAccount));
		return result;
		
	}
	/**
	 * 重置密码
	 * @param id
	 * @return 操作成功
	 */
	@RequestMapping("/resetPwd")
	@ResponseBody
	public Result resetPwd(CmsAccount cmsAccount){
		Result result =Result.getResults();
		if(getCurrentAccount()==null){
			result.setResult(-1);
			result.setMsg("非法请求！");
			return result;
		}
			cmsAccount.setPassword(Md5Utils.getMd5Encode("123456789"));
			result.setResult(cmsAccountService.setPwd(cmsAccount));
		return result;
	}
	/**
	 * 编辑用户
	 * @param cmsAccount
	 * @return
	 */
	@RequestMapping("/editAccount")
	@ResponseBody
	public Object EditAccount(CmsAccount cmsAccount){
		Result result=Result.getResults();
		result.setResult(cmsAccountService.updateAccount(cmsAccount));
		return result;
		
	}
	/**
	 * 批量删除用户
	 * @param arr
	 * @return
	 */
	@RequestMapping("/deletes")
	@ResponseBody
	public Result deletes(@RequestParam("ids")String ids){
		Result result=Result.getResults();
		if(getCurrentAccount()==null){
			result.setResult(-1);
			result.setMsg("非法请求！");
			return result;
		}
		String[]arr=ids.split(",");
		int total=0;
		for(int i=0;i<arr.length;i++){
			total+=cmsAccountService.delete(Integer.parseInt(arr[i].toString()));
		}
		result.setResult(total);
		return result;
	}
	/**
	 * 根据角色查询用户
	 * @param role
	 * @return
	 */
	@RequestMapping("/listAccountByRole")
	@ResponseBody
	public Object listAccount(@RequestParam("role")String  role){
		Result result=Result.getResults();
 		result.setValue(cmsAccountService.listAccountByRole(role));
		return result;
		
	}
	
	}