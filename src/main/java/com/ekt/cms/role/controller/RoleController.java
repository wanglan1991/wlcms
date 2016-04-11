package com.ekt.cms.role.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.swing.plaf.synth.SynthStyle;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ekt.cms.role.entity.CmsRole;
import com.ekt.cms.role.service.CmsRoleService;
/**
 * 角色管理类 2016-4-8 12：36
 * @author wanglan  
 *
 */
@Controller
@RequestMapping(value="/role")
public class RoleController {
	@Resource
	CmsRoleService cmsRoleService;
	/**
	 * 加载角色列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/manage")
	public String manager(HttpServletRequest request){
		request.setAttribute("list", cmsRoleService.getCmsRoleList() );
		return "main/role/manage";
	}
	/**
	 * 新增角色
	 * @param cmsRole
	 * @return
	 */
	@RequestMapping(value="/add")
	@ResponseBody
	public Map<String,Object> add(CmsRole cmsRole){ 
		return cmsRoleService.addCmsRole(cmsRole);
	}
	/**
	 * 根据角色Id删除角色 
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/delete")
	@ResponseBody
	public Map<String,Object> delete(@RequestParam("id")Integer id){
		return cmsRoleService.deleteCmsRole(id);
	}
	/**
	 * 编辑角色
	 * @param cmsRole
	 * @return
	 */
	@RequestMapping(value="/update")
	@ResponseBody
	public Map<String,Object>update(CmsRole cmsRole){
		System.out.println(cmsRole.getEncoding()+"--"+cmsRole.getName()+"---"+cmsRole.getId());
		return cmsRoleService.updateCmsRole(cmsRole);
	}
	
	

}
