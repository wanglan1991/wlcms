package com.ekt.cms.role.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
		return "main/role/manage";
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
		if (fieldErrors != null && fieldErrors.isEmpty()) {
			for (FieldError fieldError : fieldErrors) {
				result.setMsg(fieldError.getDefaultMessage());
			}
			result.setResult(0);
			return result;
		}

		CmsRole role = cmsRoleService.getCmsRoleByEncoding(cmsRole.getEncoding());
		if (role != null) {
			result.setMsg("错误！角色已经存在。");
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
		List<FieldError> fieldErrors = bindingResult.getFieldErrors();
		Result result = Result.getResults();
		if (fieldErrors != null && fieldErrors.isEmpty()) {
			for (FieldError fieldError : fieldErrors) {
				result.setMsg(fieldError.getDefaultMessage());
			}
			result.setResult(0);
			return result;
		}		
		CmsRole role = cmsRoleService.getCmsRoleByEncoding(cmsRole.getEncoding());
		
		
		return result;
	}
	

}
