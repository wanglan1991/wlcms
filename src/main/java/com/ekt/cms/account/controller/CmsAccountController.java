package com.ekt.cms.account.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ekt.cms.account.entity.CmsAccount;
import com.ekt.cms.account.service.ICmsAccountService;
import com.ekt.cms.common.BaseController;
import com.ekt.cms.role.service.CmsRoleService;
import com.ekt.cms.utils.AuthPassport;
import com.ekt.cms.utils.page.Pagination;
@Controller
@RequestMapping(value="/account")
public class CmsAccountController extends BaseController {
	@Resource
	private ICmsAccountService cmsAccountService;
	
	@Resource CmsRoleService cmsRoleService;
	
	@AuthPassport
	@RequestMapping(value="/manage")
	public String manage(){
		return "/user/manage";
		}

	@RequestMapping("/list")
	@ResponseBody
	public Map<String,Object> accountList(Pagination pag ,CmsAccount cmsAccount){
		pag.setPage(pag.getOffset()/pag.getLimit()+1);
		pag.setRows(pag.getLimit());
		Map<String,Object>map=new HashMap<String,Object>();
		List<CmsAccount>list=cmsAccountService.listPage(pag,cmsAccount);
		map.put("rows",list);
		map.put("total",pag.getTotal());
		return map;
	}
	
	/**
	 * 返回用户列表
	 * @return
	 */
	@RequestMapping("/roleList")
	@ResponseBody
	public Map<String,Object> getRoleList(){
		Map<String,Object>map=new HashMap<String,Object>();
		System.out.println("进来了");
		map.put("result", cmsRoleService.getCmsRoleList());
		return map;
	}
	/**
	 * 修改角色
	 */
	@RequestMapping("/roleEdit")
	@ResponseBody
	public Map<String,Object> roleEdit(CmsAccount cmsAccount ,HttpServletRequest request){
			Map<String,Object>map=new HashMap<String,Object>();
			map.put("result", cmsAccountService.update(cmsAccount));
		return map;
	}
	/**
	 * 删除用户
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Map<String,Object> accountDelete(@RequestParam("id") int id){
		Map<String,Object>map=new HashMap<String,Object>();
		map.put("result", cmsAccountService.delete(id));
		return map;
		
	}
	/**
	 * 添加用户
	 */
	@RequestMapping("/addAccount")
	@ResponseBody
	public Map<String,Object> addAccount(CmsAccount cmsAccount){
		Map<String,Object>map=new HashMap<String,Object>();
		map.put("result", cmsAccountService.addAccount(cmsAccount));
		return map;
	}
}