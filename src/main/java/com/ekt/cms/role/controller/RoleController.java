package com.ekt.cms.role.controller;
import java.util.List;
import javax.annotation.Resource;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ekt.cms.account.entity.CmsAccount;
import com.ekt.cms.common.BaseController;
import com.ekt.cms.common.entity.Result;
import com.ekt.cms.role.entity.CmsRole;
import com.ekt.cms.role.service.ICmsRoleService;
/**
 * 角色管理类 2016-4-8 12：36
 * @author wanglan  
 *
 */
import com.ekt.cms.utils.pageHelper.PageBean;
import com.ekt.cms.utils.pageHelper.PageContext;

@Controller
@RequestMapping(value = "/role")
public class RoleController extends BaseController {

	@Resource
	ICmsRoleService cmsRoleService;

	/**
	 * 返回角色管理页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/manage")
	public String manager() {
		return "main/role/roleManage";
	}

	/**
	 * 加载角色列表
	 * 
	 * @param page
	 * @param cmsRole
	 * @return
	 */
	@RequestMapping(value = "/pageList")
	@ResponseBody
	public PageBean<CmsRole> list(PageContext page, CmsRole cmsRole) {
		page.paging();
		CmsAccount account=getCurrentAccount();
		//从session中获取用户如用户不为空并且用户名等于CMSROOT就显示自己的角色
		if(account!=null&&account.getUserName().equals("CMSROOT")){
			cmsRole.setId(account.getRole());
		}
		return new PageBean<CmsRole>(cmsRoleService.listPage(cmsRole));
	}

	/**
	 * 修改角色
	 * 
	 * @param cmsRole
	 * @return
	 */
	@RequestMapping(value = "/editRole")
	@ResponseBody
	public Result editRole(@Valid CmsRole cmsRole, BindingResult bindingResult) {
		List<FieldError> fieldErrors = bindingResult.getFieldErrors();
		Result result = Result.getResults();
		if(getCurrentAccount()==null){
			result.setResult(-1);
			result.setMsg("非法请求！");
			return result;
		}
		if (fieldErrors != null && ! fieldErrors.isEmpty()) {
			for (FieldError fieldError : fieldErrors) {
				result.setMsg(fieldError.getDefaultMessage());
			}
			result.setResult(-1);
			return result;
		}

		CmsRole role = cmsRoleService.getCmsRoleByEncoding(cmsRole.getEncoding());
		if (role != null) {
			result.setMsg("错误！角色已经存在。");
			result.setResult(-1);
			return result;
		}
		result.setResult(cmsRoleService.updateCmsRole(cmsRole));
		return result;
	}
	/**
	 * 添加用户
	 * @param cmsRole
	 * @param bindingResult
	 * @return
	 */
	@RequestMapping(value = "/addRole")
	@ResponseBody
	public Result addRole(@Valid CmsRole cmsRole, BindingResult bindingResult){
		Result result = Result.getResults();
		List<FieldError> fieldErrors = bindingResult.getFieldErrors();
		if (fieldErrors != null && ! fieldErrors.isEmpty()) {
			for (FieldError fieldError : fieldErrors) {
				result.setMsg(fieldError.getDefaultMessage());
			}
			result.setResult(-1);
			return result;
		}		
		CmsRole role = cmsRoleService.getCmsRoleByEncoding(cmsRole.getEncoding());
		if(role!=null){
			result.setMsg("错误，用户编码已存在！");
			result.setResult(-1);
			return result;
		}
		result.setResult(cmsRoleService.addCmsRole(cmsRole));
		return result;
	}
	/**
	 * 删除
	 * @param ids
	 * @return
	 */
	@Transactional
	@RequestMapping(value = "/delete")
	@ResponseBody
	public Result delete(@RequestParam("ids")String ids){
		Result result=Result.getResults();
		if(getCurrentAccount()==null){
			result.setResult(-1);
			result.setMsg("非法请求！");
			return result;
		}
		String[]arr=ids.split(",");
		int total=0;
		for(int i=0;i<arr.length;i++){
			total+=cmsRoleService.deleteCmsRole(Integer.parseInt(arr[i].toString()));
			//删除所有与该角色相关的所有权限
			cmsRoleService.delPermissionByRoleId(Integer.parseInt(arr[i].toString()));
		}
		result.setResult(total);
		return result;
		
	}
	/**
	 * 停启用角色
	 * @param cmsRole
	 * @return
	 */
	@RequestMapping(value = "/confine")
	@ResponseBody
	public  Result confine(CmsRole cmsRole){
		Result result=Result.getResults();
		if(getCurrentAccount()==null){
			result.setResult(-1);
			result.setMsg("非法请求！");
			return result;
		}
		result.setResult(cmsRoleService.confine(cmsRole));
		return result;
	}
	/**
	 * 返回Tree
	 * @param roleId
	 * @return
	 */
	@RequestMapping(value = "/tree")
	@ResponseBody
	public Result getTree(@RequestParam("roleId")Integer roleId){
		Result result=Result.getResults();
			result.setValue(cmsRoleService.getTreeByRoleId(roleId));
			return  result;
	}
	/**
	 * 保存提交角色权限
	 * @param arr
	 * @param roleId
	 * @return
	 */
	@Transactional
	@RequestMapping(value = "/permissions")
	@ResponseBody
	public Result setPermissions(@RequestParam("permissionStr")String arr,@RequestParam("roleId")int roleId){
		Result result=Result.getResults();
		if(getCurrentAccount()==null){
			result.setResult(-1);
			result.setMsg("非法请求！");
			return result;
		}
		if(arr==""){
			//根据Id删除所有权限
			result.setResult(cmsRoleService.delPermissionByRoleId(roleId));
		}else{
			String[]arrs=arr.split(",");
			//根据Id删除所有权限
			cmsRoleService.delPermissionByRoleId(roleId);
			for(int i=0;i<arrs.length;i++){
				//插入角色对应权限到数据库
				cmsRoleService.insertRolePermission(Integer.parseInt(arrs[i].toString()),roleId);
			}
		}
		return result;
	}

}

